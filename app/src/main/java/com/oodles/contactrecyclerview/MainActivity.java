package com.oodles.contactrecyclerview;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {
    RecyclerView loginUserRecycler;

    String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME +
            " COLLATE LOCALIZED ASC";
    private String mSearchString = "ankita";
    // Defines the array to hold values that replace the ?
    private String[] mSelectionArgs = {mSearchString};
    private static final String[] PROJECTION = {
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone.PHOTO_URI
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        getLoaderManager().initLoader(0, null, contactLoaderManager);
        initRecycler();

    }


    private void initRecycler() {
        loginUserRecycler = (RecyclerView) findViewById(R.id.loginUserRecycler);
        loginUserRecycler.setLayoutManager(new LinearLayoutManager(this));
        loginUserRecycler.setItemAnimator(new DefaultItemAnimator());
    }


    LoaderManager.LoaderCallbacks<Cursor> contactLoaderManager = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            CursorLoader cursorLoader = new CursorLoader(MainActivity.this,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, // URI
                    PROJECTION, // projection fields
                    null , // the selection criteria
                    null, // the selection args
                    sortOrder // the sort order
            );
            return cursorLoader;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            loginUserRecycler.setAdapter(new ContactsAdapter(MainActivity.this, data));
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    };
}
