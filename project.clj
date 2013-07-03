(defproject sbc6502 "0.1.0-SNAPSHOT"
  :description "Emulate a very simple MOS Technology 6502 Single Board Computer"
  :url "https://github.com/rosti/sbc6502"
  :license {:name "Simplified BSD License"
            :url "http://opensource.org/licenses/BSD-2-Clause"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [emu6502 "0.1.0-SNAPSHOT"]]
  :main sbc6502.core)
