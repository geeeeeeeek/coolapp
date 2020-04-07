package com.geeeeeeeek.coolapp.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import com.geeeeeeeek.coolapp.R
import com.geeeeeeeek.coolapp.fragment.ContactsFragment
import com.geeeeeeeek.coolapp.groupcontacts.GroupFragment
import kotlinx.android.synthetic.main.activity_cooltab.*

/**
 * Created by XiaoQingsong
 * Date: 2020/4/3
 * Time: 8:57 AM
 */
class CoolTabActivity : FragmentActivity() {

    private var  fragments = ArrayList<Fragment>()
    private var titles = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cooltab)

        init()
    }

    private fun init() {
        fragments.add(ContactsFragment.newInstance())
        fragments.add(GroupFragment.newInstance())
        titles.add("全部")
        titles.add("分组")

        view_pager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(view_pager)
    }

    private var viewPagerAdapter = object : FragmentStatePagerAdapter(supportFragmentManager){
        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
        }
    }
}