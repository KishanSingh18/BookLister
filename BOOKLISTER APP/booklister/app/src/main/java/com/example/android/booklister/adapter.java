package com.example.android.booklister;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class adapter extends RecyclerView.Adapter<ViewHolder> {
    @NonNull
   List<books> mylist;
    public adapter(List<books> mylist){
        this.mylist=mylist;
    }
   public void updatebook(List<books> list){
       mylist.clear();
       mylist.addAll(list);
       notifyDataSetChanged();

   }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

books data = mylist.get(position);
holder.title.setText(data.title);
//StringBuilder output = new StringBuilder();
//for(int i=0;i<data.author.length;i++){
//    output.append(data.author[i]);
//    output.append(" ");
//}//holder.author.setText(output.toString());
holder.publisher.setText(data.publisher);
        Glide.with(holder.itemView.getContext()).load(data.url).into(holder.images);
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

}

class ViewHolder extends RecyclerView.ViewHolder{
    TextView title;
//    TextView author;
    TextView publisher;

    ImageView images;
    public ViewHolder(@NonNull  View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.title);
//        author=itemView.findViewById(R.id.author);
        publisher=itemView.findViewById(R.id.publisher);

        images=itemView.findViewById(R.id.image);



    }
}