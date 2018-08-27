package net.alexandroid.roomfirststepstutorial;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.alexandroid.roomfirststepstutorial.db.AppDatabase;
import net.alexandroid.roomfirststepstutorial.db.Note;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity
        implements NotesAdapter.NotesAdapterInteraction {

    private NotesAdapter mNotesAdapter;
    private Button mBtnAddNote;
    private EditText mEtTitle;
    private EditText mEtBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
        setRecyclerView();
    }

    private void setViews() {
        mBtnAddNote = findViewById(R.id.btnAddNote);
        mEtTitle = findViewById(R.id.etTitle);
        mEtBody = findViewById(R.id.etBody);

        mBtnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddNoteBtnClick();
            }
        });
    }

    private void onAddNoteBtnClick() {

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
