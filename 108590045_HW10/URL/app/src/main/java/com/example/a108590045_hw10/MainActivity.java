package com.example.a108590045_hw10;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>,AdapterView.OnItemSelectedListener {
    private EditText mUrl;
    private String InputUrl;
    private TextView HtmlTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HtmlTest = findViewById(R.id.HtmlTest);
        mUrl = findViewById(R.id.editTextTextPersonName);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence>adapter =
                ArrayAdapter.createFromResource(this,R.array.url_normalization, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        InputUrl = text;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void getWeb(View view) {
        String url = mUrl.getText().toString();
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null)
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null)
            networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() && url.length() != 0) {
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", url);
            queryBundle.putString("transferProtocol", InputUrl);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
        }else {
            if (url.length() == 0)
                HtmlTest.setText("no search");
            else
                HtmlTest.setText("no network");
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String queryString = "";
        String transferProtocol = "";

        if (args != null) {
            queryString = args.getString("queryString");
            transferProtocol = args.getString("transferProtocol");
        }

        return new GetRequestLoader(this, queryString, transferProtocol);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            HtmlTest.setText(data);
        } catch (Exception e){
            e.printStackTrace();
            HtmlTest.setText("no network");
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}