package com.freedom.notey.ui.secureNote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import com.freedom.notey.R
import com.freedom.notey.R.drawable.ic_close

class PinDialog:DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.PinDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View= inflater.inflate(R.layout.pin_dialog,container,false)
       val toolbar: Toolbar =view.findViewById(R.id.toolbar)

        toolbar.apply {
            setNavigationOnClickListener{dismiss()}
            navigationIcon= resources.getDrawable(ic_close)
        }

   return view
    }

}