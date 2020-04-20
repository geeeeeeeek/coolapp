package com.geeeeeeeek.coolapp.activity

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import com.geeeeeeeek.coolapp.R
import com.geeeeeeeek.coolapp.fragment.ContactsFragment
import com.geeeeeeeek.coolapp.groupcontacts.GroupFragment
import com.geeeeeeeek.coolapp.model.ContactBean
import com.geeeeeeeek.coolapp.model.DialogBean
import com.geeeeeeeek.coolapp.widget.ContactSearchFragment
import com.geeeeeeeek.coolapp.widget.CustomBottomSheetLayout
import com.google.android.material.tabs.TabLayout
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

        var contactBean = ContactBean()
        contactBean.name = "hhh"
        contactBean.remark = "remark"

//        ContactSearchFragment.newInstance().show(supportFragmentManager, "")

        var dialogDatas = ArrayList<DialogBean>()
        dialogDatas.add(DialogBean("WRITE", "写邮件", R.mipmap.ic_launcher))
        dialogDatas.add(DialogBean("EDIT", "编辑邮件", R.mipmap.ic_launcher))
        dialogDatas.add(DialogBean("SHARE", "分享邮件", R.mipmap.ic_launcher))
        dialogDatas.add(DialogBean("DELETE", "删除邮件", R.mipmap.ic_launcher))
        CustomBottomSheetLayout(this).setData(dialogDatas)
                .setOnClickListener {
                    Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
                }
                .show()
    }

    private fun init() {
        fragments.add(ContactsFragment.newInstance())
        fragments.add(GroupFragment.newInstance())
        titles.add("全部")
        titles.add("分组")

        view_pager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(view_pager)
        tabLayout.tabMode = TabLayout.MODE_FIXED;
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