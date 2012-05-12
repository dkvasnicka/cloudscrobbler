(ns net.danielkvasnicka.cloudscrobbler.utils.Utils
	(:gen-class
      ; :name #^{javax.inject.Named {}} net.danielkvasnicka.cloudscrobbler.utils.Utils
      :methods 
          [
           #^{:static true} [getOnlyTracksFromMixSections [java.util.Collection] java.util.Collection]
           #^{:static true} [getListenTimeForNewestMix [java.util.Collection] java.util.Date] 
           #^{:static true} [logFailedScrobbleAttempts [java.util.Collection java.lang.String] void] 
          ]
    )
    (:use [clojure.tools.logging])
)

(defn -getOnlyTracksFromMixSections [sections]
  (map #(.getTrack %) (filter #(.isTrack %) sections)))

(defn -getListenTimeForNewestMix [mixes]
  (last (sort #(.getListenTime %) mixes)))

(defn -logFailedScrobbleAttempts [results user]
  (doseq [result (filter #(= (.getStatus %) de.umass.lastfm.Result$Status/FAILED) results)]
    (error (str "Scrobbling of" (.getArtist result) " - " (.getTrack result) "for user" user "failed: " 
                (.getErrorMessage result)))))
