package com.example.catslistenmusic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.catslistenmusic.databinding.ActivityMainBinding
import com.example.catslistenmusic.model.Track
import com.example.catslistenmusic.model.toGigaTrack
import com.example.catslistenmusic.ui.LocalMusicFragment
import com.example.catslistenmusic.ui.MusicPlayerFragment
import com.example.catslistenmusic.ui.OnlineMusicFragment

class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        binding.bottomNavBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_online -> {
                    loadFragment(OnlineMusicFragment())
                    true
                }
                R.id.item_local -> {
                    loadFragment(LocalMusicFragment())
                    true
                }
                else -> false
            }
        }
        if (savedInstanceState == null) {
            loadFragment(OnlineMusicFragment())
        }
    }

    override fun loadFragment(fragment: Fragment) {
        val fragmentTag = fragment.javaClass.simpleName
        val existingFragment = supportFragmentManager.findFragmentByTag(fragmentTag)

        if (existingFragment != null) {
            supportFragmentManager.popBackStack(fragmentTag, 0)
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, fragmentTag)
                .addToBackStack(fragmentTag)
                .commit()
        }
    }

    override fun listenTrack(track: Track) {
        val gigaTrack = track.toGigaTrack()
        val fragment = MusicPlayerFragment.newInstance(gigaTrack)
        val fragmentTag = fragment.javaClass.simpleName
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment, fragmentTag)
            .addToBackStack(fragmentTag)
            .commit()
    }
}
