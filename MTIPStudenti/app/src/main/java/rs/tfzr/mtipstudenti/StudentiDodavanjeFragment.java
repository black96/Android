package rs.tfzr.mtipstudenti;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StudentiDodavanjeFragment extends Fragment
{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    protected TextView _textViewDodavanjeLabela;
    protected Context _context;
    protected Button _btnPotvrdi;
    protected Button _btnOdustani;
    protected LinearLayout _linearLayoutIDStudenta;
    protected LinearLayout _linearLauyoutMain;



    public StudentiDodavanjeFragment() {

    }

    public static StudentiDodavanjeFragment newInstance(String param1, String param2)
    {
        StudentiDodavanjeFragment fragment = new StudentiDodavanjeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_studenti_dodavanje_izmena, container, false);
        this._context = rootView.getContext();
        this._textViewDodavanjeLabela = (TextView) rootView.findViewById(R.id.textViewStudentiNazivAkcije);
        this._textViewDodavanjeLabela.setText("Dodavanje studenta");
        this._linearLauyoutMain = (LinearLayout) rootView.findViewById(R.id.dodavanjeIzmenaMainLinearLayout);
        this._linearLayoutIDStudenta = (LinearLayout) rootView.findViewById(R.id.linearLayoutIDStudenta);

        this._linearLauyoutMain.removeView(this._linearLayoutIDStudenta);
        this._btnPotvrdi = (Button) rootView.findViewById(R.id.buttonPotvrdi);
        this._btnOdustani = (Button) rootView.findViewById(R.id.buttonOdustani);

        this._btnOdustani.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view)
                                    {
                                        MTIPStudentiMain.displayView(
                                                new StudentiFragment(),
                                                                MTIPStudentiMain._activity
                                                        );
                                    }
                                })
        ;
        return rootView;
    }


    public void onButtonPressed (Uri uri) {
        if (mListener != null)
        {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else { throw new RuntimeException(context.toString()
                    +
                    " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {

        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}