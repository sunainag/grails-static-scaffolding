package grails.static.scaffolding

class Song {

	String title

    static constraints = {
    	title nullable: false
    }
}
