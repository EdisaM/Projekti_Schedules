package com.example.twin.projekti_schedules;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.example.twin.projekti_schedules.R.layout.listselector;

// List view:(views)
public class motivation extends AppCompatActivity {
    public static ArrayList<quotes> myQuotes= new ArrayList<quotes>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivation);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MenuActivity.class));
                finish();
            }
        });


        populateQuotesView();
        populateListView();
        getCount();
    }


    public void populateQuotesView(){
        myQuotes.add(new quotes("“Amateurs sit and wait for inspiration, the rest of us just get up and go to work.”","― Stephen King, On Writing: A Memoir of the Craft"));
        myQuotes.add(new quotes("“If you spend too much time thinking about a thing, you'll never get it done.” ","― Bruce Lee"));
        myQuotes.add(new quotes("“Whenever you are asked if you can do a job, tell 'em, 'Certainly I can!' Then get busy and find out how to do it.”  ","― Theodore Roosevelt"));
        myQuotes.add(new quotes("“Create with the heart; build with the mind.”  ","― Criss Jami, Killosophy"));
        myQuotes.add(new quotes("“Your mind is for having ideas, not holding them.”  ","― David Allen"));
        myQuotes.add(new quotes("“Concentrate all your thoughts upon the work in hand. The Sun's rays do not burn until brought to a focus”   ","― Alexander Graham Bell "));
        myQuotes.add(new quotes("“Consider everything an experiment.”  ","― Corita Kent "));
        myQuotes.add(new quotes("“On every level of life, from housework to heights of prayer, in all judgment and efforts to get things done, hurry and impatience are sure marks of the amateur.”  ","― Evelyn Underhill"));
        myQuotes.add(new quotes("“To be disciplined is to follow in a good way. To be self disciplined is to follow in a better way.”","― Corita Kent "));
        myQuotes.add(new quotes("“Nothing is a mistake. There’s no win and no fail. There’s only make.” ","― Corita Kent "));
        myQuotes.add(new quotes("“The elegance under pressure is the result of fearlessness.”","― Ashish Patel "));
        myQuotes.add(new quotes("“Lack of direction, not lack of time, is the problem. We all have twenty-four hour days.” ","― Zig Ziglar "));
        myQuotes.add(new quotes("“Don't be pushed around by the fears in your mind. Be led by the dreams in your heart.” ","― Roy T.Bennett "));
        myQuotes.add(new quotes("“It’s only after you’ve stepped outside your comfort zone that you begin to change, grow, and transform.” ","― Roy T.Bennett "));
        myQuotes.add(new quotes("“The only way of discovering the limits of the possible is to venture a little way past them into the impossible.” ","― Arthur C. Clarke"));
        myQuotes.add(new quotes("“The man who moves a mountain begins by carrying away small stones.” ","― Confucius, Confucius: The Analects"));
        myQuotes.add(new quotes("“Without ambition one starts nothing. Without work one finishes nothing. The prize will not be sent to you. You have to win it.” ","― Ralph Waldo Emerson"));
        myQuotes.add(new quotes("“Change the way you look at things and the things you look at change.” ","― Wayne W. Dyer"));
        myQuotes.add(new quotes("“Be brave to stand for what you believe in even if you stand alone.” ","― Roy T. Bennett"));
        myQuotes.add(new quotes("“Productivity is never an accident. It is always the result of a commitment to excellence, intelligent planning, and focused effort.”","― Paul J. Meyer"));
        myQuotes.add(new quotes("“Productivity is being able to do things you were never able to do before.”","― Franz Kafka"));
        myQuotes.add(new quotes("“Procrastinating is a vice when it comes to productivity, but it can be a virtue for creativity.”","― Adam Grant"));
        myQuotes.add(new quotes("“Whether you think you can or you think you can’t, you’re right.”","― Henry Ford"));
        myQuotes.add(new quotes("“Do or do not. There is no try”","― Yoda "));
        myQuotes.add(new quotes("“Whatever the mind of man can conceive and believe, it can achieve. ”","― Napoleon Hill "));
        myQuotes.add(new quotes("“Twenty years from now you will be more disappointed by the things that you didn’t do than by the ones you did do, so throw off the bowlines, sail away from safe harbor, catch the trade winds in your sails. Explore, Dream, Discover. ”","― Mark Twain"));
        myQuotes.add(new quotes("“Strive not to be a success, but rather to be of value.”","― Albert Einstein "));
        myQuotes.add(new quotes("“When everything seems to be going against you, remember that the airplane takes off against the wind, not with it.”","― Henry Ford"));
        myQuotes.add(new quotes("“A person who never made a mistake never tried anything new.”","― Albert Einstein"));
        myQuotes.add(new quotes("“Limitations live only in our minds. But if we use our imaginations, our possibilities become limitless. ”","― Jamie Paolinetti"));
        myQuotes.add(new quotes("“Everything you’ve ever wanted is on the other side of fear.”","― George Addair"));

    }
    public void populateListView() {
        ArrayAdapter<quotes> adapter=new MyListAdapter();
        ListView list=(ListView)findViewById(R.id.quotesListView);
        list.setAdapter(adapter);
    }



    public class MyListAdapter extends ArrayAdapter<quotes>{

        public MyListAdapter() {
            super(motivation.this, R.layout.motivational_items, myQuotes);

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            //make sure we have a view to work with
            View itemView=convertView;
            if(itemView==null){
                itemView=getLayoutInflater().inflate(R.layout.motivational_items, parent, false);

            }
            //find the quote to work with
            quotes currentQuote=myQuotes.get(position);

            //fill the view
            TextView quote=(TextView)itemView.findViewById(R.id.txtQuote);
            quote.setText(currentQuote.getQuote());

            TextView author=(TextView) itemView.findViewById(R.id.txtAuthor);
            author.setText(currentQuote.getAuthor());


            return itemView;

        }


    }


    public static ArrayList getArrayList()
    {
        return myQuotes;
    }

    public int getCount() {
        return myQuotes.size();
    }

}


