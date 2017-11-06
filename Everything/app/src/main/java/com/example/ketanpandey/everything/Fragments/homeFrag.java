package com.example.ketanpandey.everything.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ketanpandey.everything.Global;
import com.example.ketanpandey.everything.ListAdapter;
import com.example.ketanpandey.everything.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class homeFrag extends Fragment {


    public homeFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v= inflater.inflate(R.layout.fragment_home, container, false);
        int height= Global.getmainActivityscreenHeightDp();
        int width=Global.getscreenWidthDp();
        homeFragment_screen_init(v,width,height);
        return v;
    }

    private void homeFragment_screen_init(View rootLayout, int screenWidthDp, int screenHeightDp)
    {
        ListView blogPost_Listview=rootLayout.findViewById(R.id.blogPost_Listview);
        Global.setBlogTitlesList("Should you cover your webcam?");
        Global.setBlogContentList("Webcams have presented a security liability for years, yet many of us don't cover them up. Should we? Edward Snowden, Mark Zuckerberg, and former FBI director James Comey all seem to — but none of us are celebrities or heads of state, so what good does it do us?");
        Global.setUsernameList("Ashley Carman");

        Global.setBlogTitlesList("9 new trailers you should watch this week");
        Global.setBlogContentList("GHOST STORIES;STAR WARS: THE LAST JEDI;SLICE;I, TONYA;THE DISASTER ARTIST;IN THE FADE;COUNTERPART;VOYEUR;POTTERSVILLE");
        Global.setUsernameList("Jacob Kastren");

        Global.setBlogTitlesList("INSIDE THE ASTON MARTIN FACTORY");
        Global.setBlogContentList("Inside its small factory, under high ceilings supported by white metal beams, Aston Martin workers bustle. There’s an undercurrent of pulsing energy as the Aston Martin line runs at a steady, measured pace. In contrast, a new Aston Martin is started every 26 minutes.");
        Global.setUsernameList("Tamara Warren");

        Global.setBlogTitlesList("NoTitle");
        Global.setBlogContentList("This app is enabling the visually impaired by converting text into speech in 6 Indian languages. Hear2Read is an open-source text-to-speech application that aids learning for the visually impaired. The application runs on phones costing as low as Rs 7,000 and tablets as low as Rs 5,000. Presently, there are six apps – one for each language.");
        Global.setUsernameList("MEHR GILL");

        String titles[]=Global.getBlogTitlesList();
        String content[]=Global.getBlogContentList();
        String userName[]=Global.getUsernameList();
        ListAdapter adapter=new ListAdapter(getActivity(),titles,content,userName);
        blogPost_Listview.setAdapter(adapter);

    }

}
