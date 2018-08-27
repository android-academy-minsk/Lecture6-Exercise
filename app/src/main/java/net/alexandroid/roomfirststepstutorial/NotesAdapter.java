package net.alexandroid.roomfirststepstutorial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.alexandroid.roomfirststepstutorial.db.Note;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created on 11/30/2017.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> implements View.OnClickListener {

    private List<Note> mNoteList;
    private final NotesAdapterInteraction mListener;

    public NotesAdapter(NotesAdapterInteraction notesAdapterInteraction, List<Note> noteList) {
        mListener = notesAdapterInteraction;
        mNoteList = noteList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvTitle;
        public final TextView tvBody;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvTitle = view.findViewById(R.id.tvTitle);
            tvBody = view.findViewById(R.id.tvBody);
        }
    }

    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotesAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(mNoteList.get(position).getTitle());
        holder.tvBody.setText(mNoteList.get(position).getBody());

        holder.mView.setOnClickListener(this);
        holder.mView.setTag(position);
    }

    @Override
    public void onClick(View view) {
        int position = (int) view.getTag();
        Note note = mNoteList.get(position);
        mListener.onDeleteNote(note);
        mNoteList.remove(note);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mNoteList.size());
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }

    public interface NotesAdapterInteraction {
        void onDeleteNote(Note note);
    }
}
