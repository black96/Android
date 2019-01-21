package rs.tfzr.mtipstudenti;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class StudentiFragment extends Fragment {//TODO: Rename parameter arguments, choose names thatmatch
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

    public StudentiFragment() {// Required empty public constructor
    } // TODO: Rename and change types and number of parameters

    public static StudentiFragment newInstance(String param1, String param2) {
        StudentiFragment fragment = new StudentiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if
                (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pretraga, container, false);
        this._context = rootView.getContext();

        this._textViewDodajNovogStudenta = (TextView) rootView.findViewById(R.id.textViewDodajNovogStudenta);
        this._textViewDodajNovogStudenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MTIPStudentiMain.displayView(new StudentiDodavanjeFragment(), MTIPStudentiMain._activity);
            }
        });
        this._imageViewPretraga = (ImageView) rootView.findViewById(R.id.imageViewPretraga);
        this._imageViewPretraga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Treba implementirati pretragu!!").setTitle("MTIP Studenti");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
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
        //TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
