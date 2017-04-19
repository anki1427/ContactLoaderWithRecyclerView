package com.oodles.contactrecyclerview;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    private Cursor mCursor;
    private final int mNameColIdx,
            mIdColIdx;
    int phoneNumber;
    private Context mContext;


    public ContactsAdapter(Context context, Cursor cursor) {
        mCursor = cursor;
        this.mContext = context;
        mIdColIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID);
        mNameColIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        phoneNumber = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int pos) {
        View listItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contacts_list_item, parent, false);

        return new ContactViewHolder(listItemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int pos) {
        mCursor.moveToPosition(pos);
        String contactName = mCursor.getString(mNameColIdx);
        String contactNumber = mCursor.getString(phoneNumber);
        Log.e("contact number",contactNumber+"");
        long contactId = mCursor.getLong(mIdColIdx);
        Contact c = new Contact();
        c.name = contactName;
        c.number = contactNumber;
        c.profilePic = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);
        contactViewHolder.bind(c);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
}