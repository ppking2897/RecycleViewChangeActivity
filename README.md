# ToolBar頂端menu

頂端利用ToolBar，再利用setLogo可以設定右邊的圖檔或者利用setTitle可以設定標題，若要新增右邊三條線選單，則要在res那邊新增menu資料夾後

再新增menu.xml，然後再經由public boolean onCreateOptionsMenu(Menu menu)這方法將menu.xml 的 layout浮印上去到頂端的ToolBar上，

若要設定按下去時出現選單，設定

toolbar = (Toolbar)findViewById(R.id.toolBar);
toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.string.setting:
                        //TODO 通知設定的介面還沒用
                        Log.v("ppking" , " 通知設定 ");
                        break;
                }
                return false;
            }
        });
這邊的menuItem Item則是會判斷你按下三條線時，所出現的選單是按哪一個名稱，再利用switch判斷是按下哪一個,三條線內的選單由menu.xml內設定

<item
        android:id="@+id/setting"
        android:title="@string/setting"             
        android:orderInCategory="1"               //設定菜單順序
        android:icon="@drawable/ic_menu_black_24dp" //設定圖案
        app:showAsAction="never"                  //設定是否顯示
        >
</item>

#  BottomNavigationBar底部的菜單

compile 'com.ashokvarma.android:bottom-navigation-bar:1.3.0'

設定最下面的工具列，可以利用:

bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_place_black_24dp, "Place").setBadgeItem(badgeItem))
                .addItem(new BottomNavigationItem(R.drawable.ic_person_pin_circle_black_24dp, "PersonPlace"))
                .addItem(new BottomNavigationItem(R.drawable.ic_ondemand_video_black_24dp, "Video"))
                .initialise();
                
新增你下面的工具列選項，後面的setBadgeItem(badgeItem)是可以設定像是通知的樣子浮印在上面，要先在前面先設定:

BadgeItem badgeItem = new BadgeItem().setBorderWidth(1).setBackgroundColor(Color.RED).setText("NEW").setHideOnSelect(false);

若要設定按下去的監聽事件，先設定:

//bottombar觸發按鍵事件，並依照按下的position進入不同的fragment畫面呈現
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                Log.v("ppking" , "onTabSelected" + position);

                switch (position){
                    case 0:
                        getFragmentManager().beginTransaction().replace(R.id.fragmentChange,place).commit();
                        break;
                    case 1:
                        getFragmentManager().beginTransaction().replace(R.id.fragmentChange,person_place).commit();
                        break;
                }

            }

            @Override
            public void onTabUnselected(int position) {
                Log.v("ppking" , "onTabUnselected" + position);
            }

            @Override
            public void onTabReselected(int position) {
                Log.v("ppking" , "onTabReselected" + position);
            }
        });
#fragment Recycleview

compile 'com.android.support:cardview-v7:25.1.1'
compile 'com.android.support:recyclerview-v7:25.1.1'

中間畫面是以fragment 中間放置recyclerview 去做呈現,先在activity_main中間放置FrameLayout,然後再新創一個繼承fragment的class

先override public void onCreate(@Nullable Bundle savedInstanceState)

還有public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)

先將view定義為View view = inflater.inflate(R.layout.place_layout, container , false);

將你要在fragment內要呈現的layout定義出來

再來是要在fragment內設定recyclerview，在fragment內的layout創建一個Recyclerview的物件，然後再額外新創你要在Recyclerview內呈現的layout

之後利用public class Place_Adapter extends RecyclerView.Adapter<Place_Adapter.ViewHolder>去作畫面的分配，

後面<Place_Adapter.ViewHolder>的意思是我要將這Adapter內的ViewHolder，依照我的方式去重新定義

class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.place_weather_Text);
        }
    }
    
    主要是將ViewHolder內新增一個我想要放入的view，之後可以在holder內找到我所定義的view名稱
    
    
    
Adapter所要override的方法為:

public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //-------先定義一個view你要讓這Adapter要放上去的地方，再將這view定義成ViewHolder再return----
        View view = LayoutInflater.from(context).inflate(R.layout.place_recycleview,parent,false); 
        final ViewHolder holder = new ViewHolder(view);
        //---------------------------------------------------------------------------------------
        
        
        //----設定按下recyclerview時的監聽
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                int position = holder.getAdapterPosition();
                //判斷我是按下哪一格的view
                switch (position){
                    case 0:
                        //設定我按下時要跳轉的Activity
                        Log.v("ppking" , " position  " + position);
                        Intent it = new Intent(context , NorthLocation.class);
                        View textView = holder.textView;
                        String transitionName = "transitionNorthLocation";
                        //取得position後在設定intent跳轉的地方，在設定跳轉過後的動畫，一起跳轉的view
                        ActivityOptions transitionActivityOptions =
                                ActivityOptions.makeSceneTransitionAnimation((MainActivity)context , textView , transitionName);
                        context.startActivity(it ,transitionActivityOptions.toBundle());
                }

            }

        });
        return holder;
    }
        
        
        
        
public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(placeWeather.get(position));
    }
    
    holder內有我上面所定義的view名稱
    
 public int getItemCount() {
        return placeWeather.size();
    }
    
    這方法決定我要呈現的列數有幾個
    
    
# ActivityOptions.makeSceneTransitionAnimation

此方法是為了在跳轉Activity時，有跳轉的動畫

先在style內設定:
        <item name="android:windowContentTransitions">true</item>
        <!-- enable overlap of transitions -->
        <item name="android:windowAllowEnterTransitionOverlap">true</item>
        <item name="android:windowAllowReturnTransitionOverlap">true</item>
        
之後在recycleview內按下的監聽事件下:        

  String transitionName = "transitionNorthLocation";
  ActivityOptions transitionActivityOptions =
                         ActivityOptions.makeSceneTransitionAnimation((MainActivity)context , textView , transitionName);
  context.startActivity(it ,transitionActivityOptions.toBundle());

裡面的textview是要跳轉時跟著跳轉的view物件，此物件要設定時，必須要設定同樣的id以及transitionName，

還有在xml內物件內設定android:transitionName="transitionNorthLocation"都必須為一樣的名稱，才會有此物件的動畫

         //進來退出時的動畫設定
        Transition exitTrans = new Explode();
        getWindow().setExitTransition(exitTrans);

        Transition reenterTrans = new Explode();
        getWindow().setReenterTransition(reenterTrans);


