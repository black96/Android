package rs.tfzr.mtipstudenti;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StudentiIzmenaFragment
        extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private StudentiIzmenaFragment.OnFragmentInteractionListener mListener;
    protected TextView _textViewDodavanjeLabela;
    protected Context _context;
    protected Button _btnPotvrdi;
    protected Button _btnOdustani;

    public StudentiIzmenaFragment() {// Required empty public constructor
    }

    public static StudentiIzmenaFragment newInstance(String param1, String param2) {
        StudentiIzmenaFragment fragment = new StudentiIzmenaFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {// Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_studenti_dodavanje_izmena, container, false);
        this._context = rootView.getContext();
        this._textViewDodavanjeLabela = (TextView) rootView.findViewById(R.id.textViewStudentiNazivAkcije);
        this._textViewDodavanjeLabela.setText("Izmena studenta");
        this._btnPotvrdi = (Button) rootView.findViewById(R.id.buttonPotvrdi);
        this._btnOdustani = (Button) rootView.findViewById(R.id.buttonOdustani);
        this._btnOdustani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MTIPStudentiMain.displayView(new StudentiFragment(), MTIPStudentiMain._activity);
            }
        });
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
        if (context instanceof StudentiDodavanjeFragment.OnFragmentInteractionListener) {
            mListener = (StudentiIzmenaFragment.OnFragmentInteractionListener)
                    context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface
    OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
