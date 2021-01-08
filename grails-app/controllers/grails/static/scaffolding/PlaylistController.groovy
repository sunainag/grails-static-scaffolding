package grails.static.scaffolding

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.static.scaffolding.Playlist
import grails.gorm.transactions.Transactional


class PlaylistController {

    static namespace = 'scaffolding'

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100) 
        respond Playlist.list(params), model:[playlistCount: Playlist.count()] 
    }

    def show(Playlist playlist) {
        respond playlist 
    }

    @SuppressWarnings(['FactoryMethodName', 'GrailsMassAssignment'])
    def create() {
        respond new Playlist(params) 
    }

    @Transactional 
    def save(Playlist playlist) {
        if (playlist == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (playlist.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond playlist.errors, view:'create'  
            return
        }

        playlist.save flush:true

        request.withFormat {  
            form multipartForm {
                
                flash.message = message(code: 'default.created.message', args: [message(code: 'playlist.label', default: 'Playlist'), playlist.id])
                redirect playlist
            }
            '*' { respond playlist, [status: CREATED] } 
        }
    }

    def edit(Playlist playlist) {
        respond playlist 
    }

    @Transactional 
    def update(Playlist playlist) {
        if (playlist == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (playlist.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond playlist.errors, view:'edit' 
            return
        }

        playlist.save flush:true

        request.withFormat {
            form multipartForm {
                
                flash.message = message(code: 'default.updated.message', args: [message(code: 'playlist.label', default: 'Playlist'), playlist.id])
                redirect playlist 
            }
            '*' { respond playlist, [status: OK] } 
        }
    }

    @Transactional 
    def delete(Playlist playlist) {

        if (playlist == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        playlist.delete flush:true

        request.withFormat {
            form multipartForm {
                
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'playlist.label', default: 'Playlist'), playlist.id])
                redirect action: 'index', method: 'GET' 
            }
            '*' { render status: NO_CONTENT } 
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'playlist.label', default: 'Playlist'), params.id])
            }
        }
    }
}
