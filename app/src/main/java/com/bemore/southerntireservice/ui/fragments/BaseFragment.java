package com.bemore.southerntireservice.ui.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bemore.southerntireservice.R;
import com.bemore.southerntireservice.model.ParcelableArg;

import butterknife.ButterKnife;

/**
 * Created by Cody on 10/27/14.
 */
public abstract class BaseFragment extends Fragment {
    private ProgressDialog progressDialog;
//    protected ScopedBus scopedBus = new ScopedBus();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

//    protected ScopedBus getBus() {
//        return scopedBus;
//    }

    @Override
    public void onPause() {
        hideProgressDialog();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
//        scopedBus.resumed();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(getFragmentLayoutId(), container, false);

        injectViews(view);

        return view;
    }

    /**
     * Every fragment has to inflate a layout in the onCreateView method. We have added this method to
     * avoid duplicate all the inflate code in every fragment. You only have to return the layout to
     * inflate in this method when extends BaseFragment.
     */
    protected abstract int getFragmentLayoutId();

    private void injectViews(final View view) {
        ButterKnife.bind(this, view);
    }

    protected void replaceFragment(android.support.v4.app.Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.content, fragment);

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    protected void showFragmentWithSlidingTransition(Fragment fragment, boolean addToBackStack) {
        showFragmentWithTransition(fragment, R.anim.slide_in_up, R.anim.slide_out_down, R.anim.slide_in_down, R.anim.slide_out_up, addToBackStack);
    }

    protected void showFragmentWithTransition(Fragment fragment, int enter, int exit, int popEnter, int popExit, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

        fragmentTransaction.setCustomAnimations(enter, exit, popEnter, popExit);

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction
                .replace(R.id.content, fragment)
                .commit();
    }

    protected ProgressDialog getProgressDialog() {

        if (progressDialog == null && getActivity() != null) {
            progressDialog = new ProgressDialog(getActivity());
        }
        return progressDialog;
    }

    /**
     * Show a progress dialog
     *
     * @param message
     */
    protected void showProgressDialog(String message) {
        if (progressDialog == null || !progressDialog.isShowing()) {
            progressDialog = new ProgressDialog(getActivity());
        }

        progressDialog.setMessage(message);
        progressDialog.show();
    }

    /**
     * Hide the progress dialog
     */
    protected void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public static  <T extends BaseFragment> T newInstance(T fragment, ParcelableArg... args) {

        Bundle bundle = new Bundle();

        for (ParcelableArg arg : args) {
            bundle.putParcelable(arg.getArgName(), arg);
        }

        fragment.setArguments(bundle);

        return fragment;
    }
}
