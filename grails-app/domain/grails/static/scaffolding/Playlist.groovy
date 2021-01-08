package grails.static.scaffolding

class Playlist {

	String name
	//static hasMany = [songs: Song]
	Song song

    static constraints = {
    	name blank: false
    }

    String toString() {
        name
    }
}
