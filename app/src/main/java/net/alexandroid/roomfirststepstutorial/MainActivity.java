package net.alexandroid.roomfirststepstutorial;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.alexandroid.roomfirststepstutorial.db.Note;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NotesAdapter.NotesAdapterInteraction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRecyclerView();
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        NotesAdapter notesAdapter = new NotesAdapter(this, getNoteList());
        recyclerView.setAdapter(notesAdapter);
    }

    private List<Note> getNoteList() {
        List<Note> list = new ArrayList<>();
        list.add(createNote("Title 1 for tests", "Body 1 for tests"));
        list.add(createNote("Title 2 for tests", "Body 2 for tests"));
        list.add(createNote("Title 3 for tests", "Body 3 for tests"));
        return list;
    }

    @NonNull
    private Note createNote(String title, String body) {
        Note note = new Note();
        note.setTitle(title);
        note.setBody(body);
        return note;
    }

    // NotesAdapter.NotesAdapterInteraction interface methods
    @Override
    public void onDeleteNote(Note note) {

    }
}
