package hachem.example.controlefront;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AddEmploye extends AppCompatActivity  implements View.OnClickListener  {

    private EditText nom;
    private EditText prenom;
    private EditText date;
    private Button bnAdd;

    RequestQueue requestQueue;
    String insertUrl="http://10.0.0.2:8086/api/v1/Employe";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addemploye);
        nom = (EditText) findViewById(R.id.nom);
        prenom = (EditText) findViewById(R.id.prenom);
        date = (EditText) findViewById(R.id.date);
        bnAdd = (Button) findViewById(R.id.bnAdd);

        bnAdd.setOnClickListener(this);
    }
        @Override
        public void onClick(View view) {
            JSONObject jsonBody = new JSONObject();
            try {
                jsonBody.put("nom", nom.getText().toString() );
                jsonBody.put("prenom", prenom.getText().toString());
                jsonBody.put("date", date.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            requestQueue = Volley.newRequestQueue(getApplicationContext());

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                    insertUrl, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("resultat", response+"");
                    Toast.makeText(AddEmploye.this, "Employe a été ajouté avec succès", Toast.LENGTH_SHORT).show();
                    Intent refreshIntent = new Intent(AddEmploye.this, MainActivity.class);
                    startActivity(refreshIntent);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Erreur", error.toString());
                }
            });
            requestQueue.add(request);
        }


}