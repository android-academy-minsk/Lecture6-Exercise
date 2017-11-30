package net.alexandroid.roomfirststepstutorial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.alexandroid.roomfirststepstutorial.db.AppDatabase;
import net.alexandroid.roomfirststepstutorial.db.Note;

import java.util.List;

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
        return AppDatabase.getInstance(this).noteDao().getAll();
    }

    // NotesAdapter.NotesAdapterInteraction interface methods
    @Override
    public void onDeleteNote(Note note) {
        AppDatabase.getInstance(this).noteDao().delete(note);
    }
}
