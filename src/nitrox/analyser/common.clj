(ns nitrox.analyser.common)

(defmulti analyse-file (fn [type file] type))
