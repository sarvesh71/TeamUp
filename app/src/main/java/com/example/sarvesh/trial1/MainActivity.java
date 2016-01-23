package com.example.sarvesh.trial1;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {


    // public static final String TAG = "VolleyPatterns";

    EditText TeamName, Entry1, Entry2, Entry3, Name1, Name2, Name3;
    String Team_name, Entry_1, Entry_2, Entry_3, Name_1, Name_2, Name_3;
    Button Submit;
    TextView Result, Heading;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    //private static MainActivity sInstance;

    //RequestQueue reqQ;
    //static synchronized MainActivity getInstance(){
    //   return sInstance;
    // }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TeamName = (EditText) findViewById(R.id.TeamNameField);
        Entry1 = (EditText) findViewById(R.id.Entry1Field);
        Entry2 = (EditText) findViewById(R.id.Entry2Field);
        Entry3 = (EditText) findViewById(R.id.Entry3Field);

        Name1 = (EditText) findViewById(R.id.Name1Field);
        Name2 = (EditText) findViewById(R.id.Name2Field);
        Name3 = (EditText) findViewById(R.id.Name3Field);

        //Result = (TextView)findViewById(R.id.ResultField);
        Heading = (TextView) findViewById(R.id.textView);

        //Result.setText("   Hello!");

        Submit = (Button) findViewById(R.id.SubmitBtn);


        Submit.setOnClickListener(new Button.OnClickListener() {

                                      public void onClick(View v) {
                                          try {
                                              if (v == Submit) {
                                                  //Result.setText("   Please wait!");
                                                  TakeInput();
                                              }
                                          } catch (Exception e) {
                                              //Result.setText("Exception Error");
                                          }
                                      }
                                  }
        );

        TeamName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TeamName.setText("");

            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    private void TakeInput() throws JSONException {

        Pattern EntryPattern = Pattern.compile("\\d{4}\\w{2}\\d{5}");

        Team_name = TeamName.getText().toString();

        Entry_1 = Entry1.getText().toString();

        if (EntryPattern.matcher(Entry_1).matches()=true) {
            AlertDialog.Builder entry1Error = new AlertDialog.Builder(MainActivity.this);
            entry1Error.setMessage("Oops... I think there is an error in the FIRST entry number.\nPlease correct it and try again.");
            AlertDialog Entry1dialog = entry1Error.create();
            return;
        }

        Entry_2 = Entry2.getText().toString();

        if (!Entry_2.matches("(\\d{4})(\\w{2})(\\d{5})")) {
            AlertDialog.Builder entry2Error = new AlertDialog.Builder(MainActivity.this);
            entry2Error.setMessage("Oops... I think there is an error in the SECOND entry number.\nPlease correct it and try again.");
            AlertDialog Entry2dialog = entry2Error.create();
            return;
        }

        Entry_3 = Entry3.getText().toString();

        if (Entry_3.matches("(\\d{4})(\\w{2})(\\d{5})")) {
            AlertDialog.Builder entry3Error = new AlertDialog.Builder(MainActivity.this);
            entry3Error.setMessage("Oops... I think there is an error in the THIRD entry number.\nPlease correct it and try again.");
            AlertDialog Entry3dialog = entry3Error.create();
            return;
        }

        Name_1 = Name1.getText().toString();
        Name_2 = Name2.getText().toString();
        Name_3 = Name3.getText().toString();

        String url = "http://agni.iitd.ernet.in/cop290/assign0/register/";

        StringRequest strReq = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                        // Result.setText("Connection Successfull");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("teamname", Team_name);
                params.put("entry1", Entry_1);
                params.put("name1", Name_1);
                params.put("entry2", Entry_2);
                params.put("name2", Name_2);
                params.put("entry3", Entry_3);
                params.put("name3", Name_3);

                return params;
            }

        };

        RequestQueue reqQ = Volley.newRequestQueue(this);
        reqQ.add(strReq);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.sarvesh.trial1/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.sarvesh.trial1/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
