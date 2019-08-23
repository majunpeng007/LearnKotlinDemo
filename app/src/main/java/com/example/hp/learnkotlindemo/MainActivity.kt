package com.example.hp.learnkotlindemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.Menu
import android.view.MenuItem
import com.example.hp.learnkotlindemo.ui.ArticleFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //延迟初始化操作 问题 ：by lazy 和 lateinit区别
    private val mTitles by lazy {
        //MutableList可变列表
        //定义：MutableList<类型>或mutableListOf(元素1，元素2，....,元素n)
        //可以改变自身大小的数组
        mutableListOf("主页","导航","公众号","工具","项目")
    }

    private val mFragments : MutableList<Fragment> by lazy {
        mutableListOf<Fragment>()
    }

    private val mAdapter : MyPagerAdapter by lazy {
        MyPagerAdapter(supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var actionBar = supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)

        mFragments.let {
            it.add(ArticleFragment.getInstance())
            it.add(ArticleFragment.getInstance())
            it.add(ArticleFragment.getInstance())
            it.add(ArticleFragment.getInstance())
            it.add(ArticleFragment.getInstance())
        }

        mAdapter.setFragments(mFragments)
        mAdapter.setTitles(mTitles)
        view_pager.adapter = mAdapter
        view_pager.offscreenPageLimit = 5
        tablayout.setViewPager(view_pager)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}


class MyPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    var fragmentList : MutableList<Fragment> = mutableListOf()
    var titleList : MutableList<String> = mutableListOf()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }

    fun setFragments(fragment:MutableList<Fragment>) {
        fragmentList = fragment
    }

    fun setTitles(mTitles: MutableList<String>) {
        titleList = mTitles
    }

}