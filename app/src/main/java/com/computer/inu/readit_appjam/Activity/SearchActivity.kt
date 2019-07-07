package com.computer.inu.readit_appjam.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.computer.inu.readit_appjam.Adapter.ContentsRecyclerViewAdapter
import com.computer.inu.readit_appjam.Adapter.LatestSearchKeywordRVAdapter
import com.computer.inu.readit_appjam.DB.DBHelper
import com.computer.inu.readit_appjam.Data.LatestSearchKeyword
import com.computer.inu.readit_appjam.R
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_home.*

class SearchActivity : AppCompatActivity() {

    lateinit var keywordRecyclerViewAdapter: LatestSearchKeywordRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        var dataList: ArrayList<LatestSearchKeyword> = ArrayList()
        val dbHandler = DBHelper(this, null) // 최근 검색어 Database Handler
        val cursor = dbHandler.getAllKeyword()

        // list <- DB(LatestSearchKeyword)
        toList(dataList)

        keywordRecyclerViewAdapter = LatestSearchKeywordRVAdapter(this, dataList)
        rv_latestSearchKeywords.adapter = keywordRecyclerViewAdapter
        rv_latestSearchKeywords.layoutManager = LinearLayoutManager(this)

    }

    // 데이터 삭제 함수
    fun deleteData(keyWord: String) {
        val dbHandler = DBHelper(this, null)
        val cursor = dbHandler.getAllKeyword()

        if (cursor != null) {
            for (k in 1..cursor.count) {
                cursor.moveToNext()
                if (keyWord == cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_KEYWORD))) {
                    dbHandler.delete(keyWord)
                    break
                }
            }
        }
    }

    fun toList(dataList: ArrayList<LatestSearchKeyword>) {
        val dbHandler = DBHelper(this, null)
        val cursor = dbHandler.getAllKeyword()

        cursor!!.moveToFirst()
        dataList.add(
            LatestSearchKeyword(
                cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_KEYWORD))
            )
        )
        while (cursor.moveToNext()) {
            dataList.add(
                LatestSearchKeyword(
                    cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_KEYWORD))
                )
            )
        }
        cursor.close()
    }
}
