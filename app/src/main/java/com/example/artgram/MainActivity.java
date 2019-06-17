package com.example.artgram;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.img_list)
    ListView mList;

    int[] mImages= {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
            R.drawable.img7,
            R.drawable.img8
    };
    String[] description={"Born A Crime","Wisdom of Insecurity", "Decolonizing the Mind", "My Adventures As An Illustrator","Born A Crime","Wisdom of Insecurity", "Decolonizing the Mind", "My Adventures As An Illustrator"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        MainAdapter mainAdapter = new MainAdapter(this, description, mImages);
        mList.setAdapter(mainAdapter);
    }
}
