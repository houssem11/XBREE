package com.example.xbree.HomeAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xbree.Entities.Evenement;
import com.example.xbree.Entities.User;
import com.example.xbree.R;
import com.example.xbree.Utils.database.AppDataBase;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EvenementAdapter extends RecyclerView.Adapter<EvenementAdapter.myViewHolder> {

    Context mContext;
    private List<Evenement> mData;
    private List<Evenement> event_list = new ArrayList<>();
    SharedPreferences sharedPreferences, sharedPreferencesUE, sharedPreferencesU;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Button participer;
    RoundedImageView bk;
    int idus;
    TextView titleCamp, dateDebut, dateFin, price, location, username;
    private AppDataBase database;

    public EvenementAdapter(Context mContext, List<Evenement> mDataa) {
        this.mContext = mContext;
        this.mData = mDataa;
        this.notifyDataSetChanged();
    }

    public void notifyChange(List<Evenement> event){
        this.mData = event;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EvenementAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.card_item, parent, false);
        com.example.xbree.HomeAdapter.EvenementAdapter.myViewHolder vv = new com.example.xbree.HomeAdapter.EvenementAdapter.myViewHolder(v);

        sharedPreferencesUE = parent.getContext().getSharedPreferences("UserEvent", Context.MODE_PRIVATE);
        sharedPreferencesU = parent.getContext().getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
        idus = sharedPreferencesU.getInt("idUser", 0);

        database = AppDataBase.getAppDatabase(context);
        event_list = database.produitDao().getAll();

        return vv;
    }

    @Override
    public void onBindViewHolder(@NonNull EvenementAdapter.myViewHolder holder, int position) {
        Evenement evenement = mData.get(position);

        titleCamp.setText(evenement.getNom());
        dateDebut.setText(evenement.getDateDebut());
        dateFin.setText(evenement.getDateFin());
        price.setText(evenement.getPrix() + " DT");
        location.setText(evenement.getLieu());

        participer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDataBase.getAppDatabase(mContext).produitDao().delete(evenement);
                Toast.makeText(mContext,"Evenement Supprimé",Toast.LENGTH_SHORT).show();
                EvenementAdapter.this.notifyChange(
                        AppDataBase.getAppDatabase(mContext).produitDao().getAll());
            }
        });

        TextView titre = holder.pr_title;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder {
        public TextView pr_title;
        public RoundedImageView background_img;
        public ImageView favori;

        public myViewHolder(@NonNull final View itemView) {
            super(itemView);
            background_img = itemView.findViewById(R.id.background_img);
            dateDebut = itemView.findViewById(R.id.dateDebut);
            dateFin = itemView.findViewById(R.id.dateFin);
            favori = itemView.findViewById(R.id.favori);
            participer = itemView.findViewById(R.id.delete);
            price = itemView.findViewById(R.id.price);
            location = itemView.findViewById(R.id.location);
            titleCamp = itemView.findViewById(R.id.titleCamp);
            username = itemView.findViewById(R.id.username);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    database = AppDataBase.getAppDatabase(v.getContext());
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        sharedPreferences = v.getContext().getSharedPreferences("Evenement", Context.MODE_PRIVATE);
                        sharedPreferencesUE = v.getContext().getSharedPreferences("UserEvent", Context.MODE_PRIVATE);
                        Evenement ce = mData.get(position);

                    }
                }
            });


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    return false;
                }
            });

        }
    }

}