package net.alexandroid.roomfirststepstutorial;

import android.os.Bundle;

import net.alexandroid.roomfirststepstutorial.db.AppDatabase;
import net.alexandroid.roomfirststepstutorial.db.Note;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements NotesAdapter.NotesAdapterInteraction {

    private NotesAdapter mNotesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRecyclerView();
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        mNotesAdapter = new NotesAdapter(this, getNoteList());
        recyclerView.setAdapter(mNotesAdapter);
    }

    private List<Note> getNoteList() {
        AppDatabase.getInstance(this).noteDao().insertAll(
                createNote("Title 1", "Body 1"),
                createNote("Title 2", "Body 2"),
                createNote("Title 3", "Body 3")
        );
        return AppDatabase.getInstance(this).noteDao().getAll();
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
        AppDatabase.getInstance(this).noteDao().delete(note);
    }
}
