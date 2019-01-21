package rs.tfzr.mtipstudenti;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LazilyParsedNumber;

import java.net.HttpURLConnection;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

import rs.tfzr.classes.MTIPStudent;
import rs.tfzr.data.MTIPShared;
import rs.tfzr.utility.EnumHTTPMethod;
import rs.tfzr.utility.HTTPCaller;
import rs.tfzr.utility.RESTResponseData;

public class PretragaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    protected TextView _textViewDodajNovogStudenta;
    protected ImageView _imageViewPretraga;

    protected Context _context;

    protected Runnable _sutentiTask;
    protected Handler _studentiHandler;

    protected EditText _textPretraga;
    protected LinearLayout _topLevelContainer;
    protected LinearLayout _linearLayoutResults;

    protected ArrayList<MTIPStudent> _studenti;


    public PretragaFragment() {
// Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PretragaFragment newInstance(String param1,
                                               String param2) {
        PretragaFragment fragment = new PretragaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pretraga, container, false);
        this._context = rootView.getContext();

        this._studenti = new ArrayList<MTIPStudent>();
        this._topLevelContainer = rootView.findViewById(R.id.mainContainerPretraga);


        this._textViewDodajNovogStudenta = rootView.findViewById(R.id.textViewDodajNovogStudenta);
        this._textViewDodajNovogStudenta.setVisibility(View.INVISIBLE);

        this._topLevelContainer.removeView(this._textViewDodajNovogStudenta);

        this._textPretraga = rootView.findViewById(R.id.editTextPrezimePretraga);

        this._imageViewPretraga = rootView.findViewById(R.id.imageViewPretraga);
        this._imageViewPretraga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pretraga = _textPretraga.getText().toString();
                PronadjiStudente(pretraga);
            }
        });

        this._linearLayoutResults = (LinearLayout) rootView.findViewById(R.id.layoutRezultatiPretrage);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    protected void PronadjiStudente(final String prezimeStudenta)
    {
        this._studentiHandler = new Handler();

        this._sutentiTask = new Runnable() {
            @Override
            public void run() {

                // poziv http rest servisa

                HTTPPretragaStudenataJSON pretragaREQ = new HTTPPretragaStudenataJSON();
                String[] params = new String[2];
                params[0] = "studentipretraga/";
                params[1] = prezimeStudenta;

                pretragaREQ.execute(params);
            }
        };

        this._studentiHandler.post(this._sutentiTask);

    }


    protected class HTTPPretragaStudenataJSON extends AsyncTask
            <String, Void, String> {

        protected RESTResponseData _responseData = null;
        protected ProgressDialog _progressDialog = null;
        protected String strURL = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            _progressDialog = ProgressDialog.show(_context, "Molimo Vas sačekajte...", "Preuzimam podatke...", true);
        }

        @Override
        protected String doInBackground(String... strings) {



            String serviceURL = MTIPShared.get_serviceBaseURL() + strings[0] + strings[1];
            strURL = serviceURL;

            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();

            _responseData = HTTPCaller.performMethodCall(serviceURL, EnumHTTPMethod.GET, strings[1]);

            _studenti.clear();

            _studenti.addAll(Arrays.asList(
                    gson.fromJson(_responseData.get_responseData(),
                            MTIPStudent[].class)));

            return null;

        }

        @Override
        protected void onPostExecute(String s) {

            _progressDialog.dismiss();
            _linearLayoutResults.removeAllViews();

            if (_responseData.get_responseStatusCode() != HttpURLConnection.HTTP_OK) {
                Toast.makeText(_context, "Problem", Toast.LENGTH_LONG).show();
            } else {
                LayoutInflater inflater =
                        (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                for (MTIPStudent stud : _studenti) {
                    View view = inflater.inflate(R.layout.layout_studenti_universal, null);

                    LinearLayout layoutUniversal =(LinearLayout) view.findViewById(R.id.linearLayoutStudentiUniversalContainer);
                    LinearLayout layoutTasteri = (LinearLayout) view.findViewById(R.id.linearLayoutTasteri);
                    layoutUniversal.removeView(layoutTasteri);

                    TextView textViewPrezime =view.findViewById(R.id.textViewPrezimeDATA);
                    TextView textViewIme =view.findViewById(R.id.textViewImeDATA);
                    TextView textViewBrojIndeksa =view.findViewById(R.id.textViewBrojIndeksaDATA);
                    TextView textViewStudijskiProgram = view.findViewById(R.id.textViewStudijskiProgramDATA);

                    textViewPrezime.setText(stud.get_prezime());
                    textViewIme.setText(stud.get_ime());
                    textViewBrojIndeksa.setText(stud.get_index());
                    textViewStudijskiProgram.setText(stud.get_smer().get_nazivSmera());
                    _linearLayoutResults.addView(view);

                }
            }

        }
    }
}
