import java.lang.RuntimeException

class NotesService {
    var notes: MutableList<Note> = mutableListOf()
    var noteComments: MutableList<Comment> = mutableListOf()

    fun add(note: Note /*, privacyView:String,privacyComment:String*/): Int { //Создает новую заметку у текущего пользователя.
        if (notes.isEmpty()) {
            notes.add(note)
        } else {
            note.id = notes.last().id + 1
            notes.add(note)
        }

        println("Note created")
        return note.id
    }

    fun printNotes() {
        notes.forEachIndexed { index, post ->
            println("${notes[index].ownerId} пишет: ${notes[index].text}, id записи - ${notes[index].id} \n")
        }
    }

    fun printComments() {
        noteComments.forEachIndexed { index, comment ->
            if (!noteComments[index].ifDeleted) {
                println("${noteComments[index].ownerId} пишет: ${noteComments[index].messageText},к заметке с id - ${noteComments[index].id} \n")
            }
        }
    }

    fun createComment(comment: Comment): Int { //Добавляет новый комментарий к заметке.
        var success = false
        notes.forEachIndexed { index, Note ->
            if (comment.noteId == notes[index].id) {
                comment.id = notes[index].comments + 1
                notes[index].comments++
                success = true
                noteComments.add(comment)
            }
        }

        if (success) {
            println("Comment created")
        } else throw NoteNotFoundException("no note with id: ${comment.noteId}")
        return comment.id
    }


    fun delete(noteId: Int): Boolean { //Удаляет заметку текущего пользователя.
        var success = false
        notes.forEachIndexed { index, Note ->
            if (noteId == notes[index].id) {
                val result = notes.toMutableList()
                result.removeAt(index)
                notes = result.toMutableList()
                println("note is deleted")
                success = true
            }
        }
        if (success) {
            println("Note is deleted")
        } else throw NoteNotFoundException("no note with id: ${noteId}")
        return success
    }

    fun deleteComment(commendId: Int): Boolean { //Удаляет комментарий к заметке.
        var success = false
        noteComments.forEachIndexed { index, Comment ->
            if (commendId == noteComments[index].id) {
                noteComments[index].ifDeleted = true
                println("comment is deleted")
                success = true
            }
        }
        if (success) {
            println("Comment is deleted")
        } else throw CommentNotFoundException("no comment with id: $commendId")
        return success
    }


    fun edit(note: Note): Boolean { //Редактирует заметку текущего пользователя.
        var success: Boolean = false
        notes.forEachIndexed { index, Note ->
            if (note.id == notes[index].id) {
                val target = note.copy(ownerId = notes[index].ownerId, date = notes[index].date)
                notes[index] = target
                success = true
            }
        }
        if (success) {
            println("Note is edited")
        } else throw NoteNotFoundException("no note with id: ${note.id}")
        return success
    }

    fun editComment(comment: Comment): Boolean { // Редактирует указанный комментарий у заметки.
        var success = false
        if (comment.messageText.length < 2) throw CommentTooShortException("Less than 2 characters in the comment")
        noteComments.forEachIndexed { index, Comment ->
            if (comment.id == noteComments[index].id) {
                if (noteComments[index].ifDeleted == true) {
                    throw CommentNotFoundException("no comment with id: ${comment.id}")
                } else {
                    noteComments[index] = comment.copy()
                    success = true
                }
            }
        }
        if (success) {
            println("Comment is edited")
        } else throw CommentNotFoundException("no comment with id: ${comment.id}")
        return success

    }

    fun get(noteIds: IntArray): MutableList<Note> { //Возвращает список заметок, созданных пользователем.
        var searchedNotes: MutableList<Note> = mutableListOf()
        var success = false
        noteIds.forEachIndexed { index, ids ->
            notes.forEachIndexed { index, Note ->
                if (ids == notes[index].id) {
                    searchedNotes.add(notes[index])
                    success = true
                }
            }
        }
        if (success) {
            println("Note is found")
        } else throw NoteNotFoundException("no note with ids: ${noteIds.toString()}")
        return searchedNotes
    }

    fun getById(noteId: Int): Note { //Возвращает заметку по её id.
        var note = Note()
        var success = false
        notes.forEachIndexed { index, Note ->
            if (noteId == notes[index].id) {
                note = notes[index]
                success = true
            }
        }
        if (success) {
            println("Note is found")
        } else throw NoteNotFoundException("no note with id: $noteId")
        return note
    }

    fun getComments(noteId: Int): MutableList<Comment> { //Возвращает список комментариев к заметке.
        var searchedComments: MutableList<Comment> = mutableListOf()
        var success = false
        noteComments.forEachIndexed { index, Comment ->
            if (noteId == noteComments[index].noteId) {
                searchedComments.add(noteComments[index])
                success = true
            }
        }

        if (success) {
            println("All comments are found")
        } else throw CommentNotFoundException("no comments for note with ids: $noteId")
        return searchedComments
    }

    fun restoreComment(commentId: Int): Boolean { //Восстанавливает удалённый комментарий.
        var success: Boolean = false
        noteComments.forEachIndexed { index, Comment ->
            if (commentId == noteComments[index].id) {
                noteComments[index].ifDeleted = false
                success = true
            }
        }

        if (success) {
            println("Comment is restored")
        } else throw CommentNotFoundException("no comments with ids: $commentId")
        return success
    }

    class NoteNotFoundException(message: String) : RuntimeException(message)

    class CommentNotFoundException(message: String) : RuntimeException(message)

    class CommentTooShortException(message: String) : RuntimeException(message)
}
