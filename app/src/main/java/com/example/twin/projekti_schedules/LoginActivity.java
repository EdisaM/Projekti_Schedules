package com.example.twin.projekti_schedules;

/**
 * Created by Edisa on 5/24/2018.
 */
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    //Declaration EditTexts
    EditText editTextEmail;
    EditText editTextPassword;
    TextView forgotpass;

    //Declaration TextInputLayout
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    //Declaration Button
    Button buttonLogin;

    //Declaration SqliteHelper
    SqliteHelper sqliteHelper;

    public static ArrayList<quotes> myQuotes= new ArrayList<quotes>();

    //public static final String MyPREFERENCES = "MyPrefs" ;
    //SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        forgotpass=(TextView)findViewById(R.id.forgotPass);

        sqliteHelper = new SqliteHelper(this);
        initCreateAccountTextView();
        initViews();

        //sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, forgotPassword.class);
                startActivity(intent);
            }


        });



        //session class instance

        //set click event of login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check user input is correct or not
                if (validate()) {

                    //Get values from EditText fields
                    String Email = editTextEmail.getText().toString();
                    String Password = editTextPassword.getText().toString();

                    //Authenticate user
                    User currentUser = sqliteHelper.Authenticate(new User(null, null, Email, Password));


                    //Check Authentication is successful or not
                    if (currentUser != null) {
                        Snackbar.make(buttonLogin, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();
                        //SharedPreferences.Editor editor = sharedpreferences.edit();

                       //Intent intent=new Intent(LoginActivity.this,FaqjaKryesore.class);
                        //startActivity(intent);
                        //emptyInputEditText();
                        //finish();

                        populateQuotesView();

                        quotes currentQuote=myQuotes.get(new Random().nextInt(myQuotes.size()));


                        ViewDialogPopUp alert = new ViewDialogPopUp();
                        alert.showDialog(LoginActivity.this,currentQuote.getQuote(), currentQuote.getAuthor());
                    } else {

                        //User Logged in Failed
                        Snackbar.make(buttonLogin, "Failed to log in , please try again", Snackbar.LENGTH_LONG).show();

                    }


                }
            }
        });


    }

    //this method used to set Create account TextView text and click event( maltipal colors
    // for TextView yet not supported in Xml so i have done it programmatically)
    private void initCreateAccountTextView() {
        TextView textViewCreateAccount = (TextView) findViewById(R.id.textViewCreateAccount);
        textViewCreateAccount.setText(fromHtml("<font color='#ffffff'>I don't have account yet. </font><font color='#0c0099'>create one</font>"));
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //this method is used to connect XML views to its Objects
    private void initViews() {
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        PreferenceUtils utils = new PreferenceUtils();

        if (utils.getEmail(this) != null ){
            Intent intent = new Intent(LoginActivity.this, FaqjaKryesore.class);
            startActivity(intent);
        }else{

        }

    }

    //This method is for handling fromHtml method deprecation
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    //This method is used to validate input given by user
    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();

        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            textInputLayoutEmail.setError("Please enter valid email!");
        } else {
            valid = true;
            textInputLayoutEmail.setError(null);
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPassword.setError("Please enter valid password!");
        } else {
            if (Password.length() > 5) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password is to short!");
            }
        }
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (sqliteHelper.checkUser(email, password)) {
            PreferenceUtils.saveEmail(email, this);
            PreferenceUtils.savePassword(password, this);
            //Intent accountsIntent = new Intent(this, FaqjaKryesore.class);
            //accountsIntent.putExtra("EMAIL", editTextEmail.getText().toString().trim());
            //emptyInputEditText();
            //startActivity(accountsIntent);
            //finish();
        } else {
            //Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }

        return valid;
    }

    private void emptyInputEditText(){
        editTextEmail.setText(null);
        editTextPassword.setText(null);
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








}