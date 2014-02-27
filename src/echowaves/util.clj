(ns echowaves.util
  (:require [noir.io :refer [resource-path]]
            [noir.session :as session]
            [environ.core :refer [env]])
  (:import java.io.File))

(def thumb-prefix "thumb_")

(defn session-wave-path []
  (str (resource-path) "img" File/separator (session/get :wave) File/separator))

;; (defn random-string [length]
;;   (let [ascii-codes (concat (range 48 58) (range 66 91) (range 97 123))]
;;     (apply str (repeatedly length #(char (rand-nth ascii-codes))))))

;; (defn generate-token []
;;   (random-string 255))


(defn send-push-notification [message badge tokens]
  (javapns.Push/combined message
                         badge
                         "default"
                         (env :ew-push-cert)
                         (env :ew-push-cert-pass)
                         (boolean (Boolean/valueOf (env :ew-push-prod)))
                         tokens))
