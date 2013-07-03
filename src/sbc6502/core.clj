(ns sbc6502.core
  (:gen-class :main true)
  (:use emu6502.core
        emu6502.memory-map
        emu6502.intel-hex))

(defn do-nothing [& args] 0)

(defn put-on-console
  [data address value]
  (print (char value))
  (flush))

(defn get-from-console
  [data address]
  (if (zero? (.available System/in)) 0
    (let [value (.read System/in)]
      (if (= 0x0A value) 0x0D value))))

(defn -main
  [& args]
  (when (empty? args)
    (println "Please, specify at least one hex file.")
    (System/exit 1))
  (let [mem-map (-> (empty-memory-map)
                    (bss-area 0x0000 0xF000)
                    (bss-area 0xF100 0x0F00)
                    (map-area 0xF001 0xF001 do-nothing put-on-console nil)
                    (map-area 0xF004 0xF004 get-from-console do-nothing nil))]
    (doseq [file args]
      (load-path mem-map file))
    (try
      (run-continuous (new-cpu-state mem-map))
      (catch RuntimeException e (println (.getMessage e))))))
