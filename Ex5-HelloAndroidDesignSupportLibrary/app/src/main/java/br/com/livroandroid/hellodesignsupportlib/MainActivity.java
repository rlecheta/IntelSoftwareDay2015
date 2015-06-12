package br.com.livroandroid.hellodesignsupportlib;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }

        String[] items = new String[]{
                "Floating Action Button + Snackbar",
                "CoordinatorLayout",
                "RecyclerView + FAB",
                "TabLayout",
                "CollapsingToolbar"
        };

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            switch (position) {
                case 0:
                    show(new Intent(this, ExemploFloatingButtonActivity.class));
                    break;
                case 1:
                    show(new Intent(this, ExemploCoordinatorLayoutActivity.class));
                    break;
                case 2:
                    show(new Intent(this, ExemploRecyclerViewActivity.class));
                    break;
                case 3:
                    show(new Intent(this, ExemploTabLayoutActivity.class));
                    break;
                case 4:
                    show(new Intent(this, ExemploCollapsingToolbarActivity.class));
                    break;
                default:
                    finish();
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Erro :" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void show(Intent intent) {
        // Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent,
                    ActivityOptions
                            .makeSceneTransitionAnimation(this).toBundle());
        } else {
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("livroandroid", "Mem Total: " + (Runtime.getRuntime().totalMemory() / 1024) + " mb");
        Log.d("livroandroid","Mem Free: " + (Runtime.getRuntime().freeMemory() / 1024) + " mb");
    }
}