(ns net.danielkvasnicka.cloudscrobbler.utils.Utils
	(:gen-class
      ; :name #^{javax.inject.Named {}} net.danielkvasnicka.cloudscrobbler.utils.Utils
      :methods 
          [
           #^{:static true} [getOnlyTracksFromMixSections [java.util.Collection] java.util.Collection]
           #^{:static true} [getListenTimeForNewestMix [java.util.Collection] java.util.Date] 
           #^{:static true} [doScrobble [java.util.Collection de.umass.lastfm.Session] void]            
          ]
    )
    (:use [clojure.tools.logging])
)

(defn -getOnlyTracksFromMixSections [sections]
  (map #(.getTrack %) (filter #(.isTrack %) sections)))

(defn -getListenTimeForNewestMix [mixes]
  (.getListenTime (last (sort-by #(.getListenTime %) mixes))))

; --- Engine stuff
(defn logFailedScrobbleAttempts [results user]
  (doseq [result (filter #(= (.getStatus %) de.umass.lastfm.Result$Status/FAILED) results)]
    (error (str "Scrobbling of " (.getArtist result) " - " (.getTrack result) " for user " user " failed: " 
                (.getErrorMessage result)))))

(defn -doScrobble [tracks session]
  (doseq [tracksBatch (partition-all 50 tracks)] 
    (logFailedScrobbleAttempts (de.umass.lastfm.Track/scrobble 
      (map #(de.umass.lastfm.scrobble.ScrobbleData. (.getArtist %) (.getName %) (/ (System/currentTimeMillis) 1000)) tracksBatch)
      session) (.getUsername session))))
