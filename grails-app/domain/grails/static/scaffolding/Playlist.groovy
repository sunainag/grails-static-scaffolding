package grails.static.scaffolding

import groovy.transform.ToString

@ToString
class Playlist {

	String name
	static hasMany = [songs: Song]
	//Song song

    static constraints = {
    	name blank: false
    	songs unique: true
    }


}
