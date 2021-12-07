package my.mummyapp.bestmom.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import my.mummyapp.bestmom.ui.models.DataModel;

import java.util.List;

@Database(entities = {DataModel.class},version =1)
public  abstract class DataModelDatabase extends RoomDatabase {
    private static DataModelDatabase instance;

    public abstract daodatamodel daoProgressData();


    public DataModel dataModel;
    public  static List<DataModel> datmodelList;

    public static synchronized DataModelDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DataModelDatabase.class,
                    "Progressdatabase").fallbackToDestructiveMigration().addCallback(RoomDataCallBack)
                    .build();

        }
        return instance;

    }

    private static RoomDatabase.Callback RoomDataCallBack = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateData(instance).execute();
            Log.d("oncreateddatabase", "onCreate: " +instance.toString());
        }
    };

    private static class PopulateData extends AsyncTask<Void, Void, Void> {
        daodatamodel daodatamodel;
        private LiveData<List<DataModel>> datmodelList;

        public PopulateData(DataModelDatabase db) {

            this.daodatamodel = db.daoProgressData();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            daodatamodel.insertProgress(new DataModel(1, 0, 0,"النهاردة اعملى لطفلك الاكل الى يحبه و قوليله انك فخورة بيه"));
            daodatamodel.insertProgress(new DataModel(2, 0, 0,"النهاردة طلعى البوم صورطفلك و هو صغير و احكى لهم قصص عن هذة الفترة"));

            daodatamodel.insertProgress(new DataModel(3, 0, 0,"النهاردة اندمجى مع طفلك فى لعبه ممتعه كالتلوين بالوان المياة"));

            daodatamodel.insertProgress(new DataModel(4, 0, 0,"النهاردة خلى طفلك يختار ملابس النزول بنفسه و امدحى حسن اختيارة "));

            daodatamodel.insertProgress(new DataModel(5, 0, 0,"النهاردة شاركى طفلك وجبه الغداء او الفطور مع التحدث سويا"));

            daodatamodel.insertProgress(new DataModel(6, 0, 0,"النهاردة اكتبى لطفلك رساله حب او رسمه صغيرة و ضعيها بجانب فطورة"));

            daodatamodel.insertProgress(new DataModel(7, 0, 0,"النهاردة احضنى طفلك و قبليه بوجهه مبتسم فالصباح و قبل النوم"));

            daodatamodel.insertProgress(new DataModel(8, 0, 0,"النهاردة اذهبى مع طفلك الى سينما لمشاهدة الكارتون او التنزة بحديقه"));

            daodatamodel.insertProgress(new DataModel(9, 0, 0,"النهاردة خلى طفلك يساعدك باعداد الطعام و اقضو وقت ممتع "));

            daodatamodel.insertProgress(new DataModel(10, 0, 0,"النهاردةشغلى موسيقى ممتعه للاطفال و شاركى طفلك الرقص"));

            daodatamodel.insertProgress(new DataModel(11, 0, 0,"النهاردة فاجئى طفلك بهديه مثلا لعبه صغيرة "));

            daodatamodel.insertProgress(new DataModel(12, 0, 0,"النهاردة اذهبى مع طفلك لشراء مستلزمات المنزل و تبادلا الحديث الممتع "));

            daodatamodel.insertProgress(new DataModel(13, 0, 0,"النهاردة التقطى صور كثيرة مرحه مع طفلك "));

            daodatamodel.insertProgress(new DataModel(14, 0, 0,"النهاردةقبلى طفلك كتيرا على جبهته و اخبريه انه افضل هديه من ربنا"));

            daodatamodel.insertProgress(new DataModel(15, 0, 0,"النهاردةتحدثى مع طفلك عن اكثر الاشياء التى تضايقه منك و خاولى اجتنابها"));

            daodatamodel.insertProgress(new DataModel(16, 0, 0,"النهاردةزينى غرفه طفلك  بالكثير من البلونات و اتركيله رساله حب"));

            daodatamodel.insertProgress(new DataModel(17, 0, 0,"النهاردة العبى مع طفلك لعبه حرب المخدات "));

            daodatamodel.insertProgress(new DataModel(18, 0, 0,"النهاردة اتكلمى مع طفلك و اخبريه عن اكتر صفه تحبيها فيه"));

            daodatamodel.insertProgress(new DataModel(19, 0, 0,"النهاره ضعى صورة تجمعكما سويا فى غرفته"));

            daodatamodel.insertProgress(new DataModel(20, 0, 0,"النهاردة شاركى طفلك النوم بغرفه لمرة شهريا"));

            daodatamodel.insertProgress(new DataModel(21, 0, 0,"النهاردة العبى معاة بالعابه المفضله و اقضو وقت ممتع"));

            daodatamodel.insertProgress(new DataModel(22, 0, 0,"النهاردة اعملى فشار و اتفرجى مع طفلك على كارتونه المفضل"));

            daodatamodel.insertProgress(new DataModel(23, 0, 0,"النهاردة شاركى طفلك رياضته المفضله مثل لعب كرة القدم سويا"));

            daodatamodel.insertProgress(new DataModel(24, 0, 0,"النهاردة خلى طفلك يشاركك تصفيف شعرك او وضع الميكاب الخاص بكى"));

            daodatamodel.insertProgress(new DataModel(25, 0, 0,"النهاردة خلى طفلك يشاركك فى تقديم عمل خيرى كمساعدة احد المحتاجين ماديا"));

            daodatamodel.insertProgress(new DataModel(26, 0, 0,"النهاردة قومى انت و طفلك بترتيب اغراضه و لعبه "));

            daodatamodel.insertProgress(new DataModel(27, 0, 0,"النهاردة اعملى تورته منزليه بمساعدة طفلك و احتفلو سوا"));

            daodatamodel.insertProgress(new DataModel(28, 0, 0,"النهاردة اعطى لطفلك يوم راحه من الواجبات المدرسيه و الانشطه و خليه يختار يومه"));

            daodatamodel.insertProgress(new DataModel(29, 0, 0,"النهاردةاعجنى انت و طفلك عجينه بيتزا او خبز مع قضاء وقت ممتع"));

            daodatamodel.insertProgress(new DataModel(30, 0, 0,"النهاردشاركى طفلك لعبه عالكمبيوتر او عالموبايل مع الضحك سويا "));


            return null;

        }

    }
}








