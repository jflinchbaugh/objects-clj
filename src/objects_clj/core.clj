(ns objects-clj.core
  (:refer-clojure :exclude [import use])
  (:require [clojure.string :as str]
            [scad-clj.model :refer :all]
            [scad-clj.scad :refer :all]))

(defn demo []
  (spit
   "demo.scad"
   (write-scad
    (fn! 60)
    (for [x (range 0 400 100)]
      (translate [x 0 0]
                 (union
                  (cube 100 100 100)
                  (sphere 70)))))))

(def models ["soap-dish", "demo"])

(defn -main [& args]
  (if (empty? args)
    (println "Models:\n " (str/join "\n  " models))
    (let [[nm] args]
      (println (symbol (str "objects-clj." nm "/build")))
      (println (resolve (symbol (str "objects-clj." nm "/build")))))))

(defn as-hole [s] (+ 0.01 s))

(comment

  (println (resolve (symbol (str "objects-clj." "soap-dish" "/builds"))))
  (println (resolve (symbol (str "objects-clj." "soap-dish" "/builds"))))



  )

