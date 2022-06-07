package com.example.uts_10119078_adamfirdausdarmawan.ui.slideshow;
/*
    Nama : Adam Firdaus Darmawan
    Kelas : IF-2
    NIM : 10119078
 */
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SlideshowViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SlideshowViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}