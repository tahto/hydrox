(ns hydrox.common.data)

;; {:source     {<file> {<ns> {<var> <>}}}
;;  :test       {<file> {<ns> {<var> <>}}}
;;  :namespace  {<ns> <file>}}

(defrecord Registry []
  Object
  (toString [obj]
    (str "#registry" (->> obj (into {})))))

(defmethod print-method Registry
  [v w]
  (.write w (str v)))

(defn registry
  ([] (Registry.))
  ([m] (map->Registry m)))

;; {:project <> :articles <> :reference <> :template <> :registry <>}

(defrecord Folio []
  Object
  (toString [obj]
    (str "#folio" (-> obj keys vec))))

(defmethod print-method Folio
  [v w]
  (.write w (str v)))

(defn folio
  "constructs a folio object"
  {:added "0.1"}
  ([m] (map->Folio m)))

;; {<ns> {<var> {:source <> :docs <> :meta <>}}}

(defrecord References []
  Object
  (toString [obj]
    (str "#references"
         (reduce-kv (fn [nsm nsk vars]
                      (assoc nsm nsk
                             (reduce-kv (fn [vm vk vs]
                                          (assoc vm vk (-> vs keys sort vec)))
                                        {}
                                        vars)))
                    {}
                    obj))))

(defmethod print-method References
  [v w]
  (.write w (str v)))

(defn references
  "constructs a reference object"
  {:added "0.1"}
  ([]  (References.))
  ([m] (map->References m)))
