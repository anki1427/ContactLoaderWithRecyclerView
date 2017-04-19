package com.oodles.contactrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


/**
 * Contains a Contact List Item
 */
public class ContactViewHolder extends RecyclerView.ViewHolder {
    private RoundedImageView mImage;
    private TextView mLabel, userNumber;
    private Contact mBoundContact; // Can be null

    public ContactViewHolder(final View itemView) {
        super(itemView);
        mImage = (RoundedImageView) itemView.findViewById(R.id.rounded_iv_profile);
        mLabel = (TextView) itemView.findViewById(R.id.tv_label);
        userNumber = (TextView) itemView.findViewById(R.id.userNumber);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBoundContact != null) {
                    Toast.makeText(
                            itemView.getContext(),
                            "Hi, I'm " + mBoundContact.name,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void bind(Contact contact) {
        mBoundContact = contact;
        mLabel.setText(contact.name);
        userNumber.setText(contact.number);
        Picasso.with(itemView.getContext())
                .load(contact.profilePic)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(mImage);
    }
}
