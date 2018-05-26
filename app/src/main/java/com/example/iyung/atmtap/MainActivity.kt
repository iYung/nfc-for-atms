package com.example.iyung.atmtap

import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import android.content.Intent
import android.support.v4.view.ViewPager


class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val myIntent = Intent(this, Deposit::class.java)
                myIntent.putExtra("account", currentAccount)
                myIntent.putExtra("action", "Deposit")
                startActivity(myIntent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                val myIntent = Intent(this, Withdraw::class.java)
                myIntent.putExtra("account", currentAccount)
                myIntent.putExtra("action", "Withdrawal")
                startActivity(myIntent)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        pager.adapter = mSectionsPagerAdapter
        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                currentAccount = when (position) {
                    0 -> "TD Chequing"
                    1 -> "TD Savings"
                    2 -> "CIBC Chequing"
                    else -> "CIBC Savings"
                }
            }

        })

        navigation.menu.getItem(0).isCheckable = false;
        navigation.menu.getItem(1).isCheckable = false;
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return PlaceholderFragment.newInstance(position + 1)
        }

        override fun getCount(): Int {
            return 4
        }
    }

    class PlaceholderFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_main, container, false)
            var int = arguments?.getInt(ARG_SECTION_NUMBER)
            if (int == 1) {
                rootView.imageView.setImageDrawable(resources.getDrawable(R.drawable.ic_card))
                rootView.section_label.text = "TD Chequing"
            } else if (int == 2) {
                rootView.imageView.setImageDrawable(resources.getDrawable(R.drawable.ic_card))
                rootView.section_label.text = "TD Savings"
            } else if (int == 3) {
                rootView.imageView.setImageDrawable(resources.getDrawable(R.drawable.ic_card2))
                rootView.section_label.text = "CIBC Chequing"
            } else if (int == 4) {
                rootView.imageView.setImageDrawable(resources.getDrawable(R.drawable.ic_card2))
                rootView.section_label.text = "CIBC Savings"
            }
            return rootView
        }

        companion object {

            private val ARG_SECTION_NUMBER = "section_number"

            var accountName = ""

            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }

    companion object {
        var currentAccount = "TD Chequing"
    }
}
