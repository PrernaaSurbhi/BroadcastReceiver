package com.example.broadcastrecv

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.EditText
import androidx.appcompat.widget.ShareActionProvider
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.editor.*
import kotlinx.android.synthetic.main.editor.view.*


class NoteFragment:Fragment(){

    var share:ShareActionProvider? = null
    var shareIntent:Intent = Intent(Intent.ACTION_SEND).setType("text/plain")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val result=inflater.inflate(R.layout.editor, container, false)
        val editor=result.findViewById(R.id.editor_edit_text) as EditText

        editor.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                shareIntent.putExtra(Intent.EXTRA_TEXT,s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        result.close.setOnClickListener {
            editor_layout.visibility = View.GONE
        }
        return(result)
    }


    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.notes,menu)
        val shareItem:MenuItem = menu.findItem(R.id.share)
        val myShareActionProvider:ShareActionProvider = MenuItemCompat.getActionProvider(shareItem) as ShareActionProvider
        myShareActionProvider.setShareIntent(shareIntent)
        super.onCreateOptionsMenu(menu, menuInflater)
    }

}