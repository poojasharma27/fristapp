package com.firstapp.ui.home.image

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.firstapp.R
import com.firstapp.base.BaseFragment
import com.firstapp.databinding.FragmentImagesBinding
import com.firstapp.db.AppDataBase
import com.firstapp.db.entities.ImagesEntity
import com.firstapp.util.showToastLong
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream


class ImagesFragment : BaseFragment() {
    var binding: FragmentImagesBinding? = null
    private var selectImageList = arrayOf("Take Photo", " Choose From Gallery", "Cancel")
    private var isLoading: Boolean = false
    private var setPicture: ActivityResultLauncher<Intent>? = null
    var imagesEntity: ImagesEntity? = null
    var userChooseTask = "Choose From Gallery"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentImagesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.ivImageSelect?.setOnClickListener {
            openDialog()
        }

        setPicture =
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) {
                if (it.resultCode == Activity.RESULT_OK) {
                    val datapath: Intent? = it.data
                    var isCamera: Boolean = isLoading
                    if (isCamera) {
                        val extras: Bundle? = datapath?.extras
                        val imageBitmap = extras?.get("data") as Bitmap?
                        context.let {
                            val uri = getImageUri(it, imageBitmap!!, "imges")
                            imagesEntity = ImagesEntity(
                                images = uri.toString()
                            )
                        }

                    } else {
                        val fileuri: Uri? = datapath?.data

                        imagesEntity = ImagesEntity(
                            images = fileuri.toString()
                        )
                    }

                }
                imagesEntity?.let { it1 -> updateDb(it1) }
                Log.d("takePhoto", imagesEntity.toString())
            }

        getFromDb()
    }


    private fun getFromDb() {
        launch {
            context?.let {
                val ImagesEntityList = AppDataBase.invoke(it).userDetailsDao().getImagesEntity()
                val list = ArrayList<ImagesEntity>()
                list.addAll(ImagesEntityList)
                binding?.rvImage?.apply {
                    this.adapter = ImageSListAdapter(list)
                }
            }


        }

    }



    private fun updateDb(imagesEntity: ImagesEntity) {
        launch {
            context?.let {
                val articleEntity = AppDataBase.invoke(it).userDetailsDao()
                    .addImages(imagesEntity)

                Log.d("articleEntity", articleEntity.toString())
            }
        }

    }

    private fun openDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(getString(R.string.addphoto))
            .setItems(
                selectImageList
            ) { dialog, which ->
                when (which) {
                    0 ->
                        permission()
                    1 -> {
                        openGallery()
                    }
                    3 ->
                        dialog.dismiss()

                }
            }
        builder.show()
    }

    private fun openGallery() {
        isLoading = false
        val galleryIntent = Intent(Intent.ACTION_GET_CONTENT)
        galleryIntent.setType("image/*")
        setPicture?.launch(galleryIntent)
    }

    private fun openCamera() {
        isLoading = true
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        setPicture?.launch(cameraIntent)
    }


    fun getImageUri(inContext: Context?, inImage: Bitmap?, imageName: String?): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage?.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        try {
            // val path:String = MediaStore.Images.Media.insertImage(inContext?.contentResolver, inImage, imageName, null)
            return Uri.parse(
                MediaStore.Images.Media.insertImage(
                    inContext?.contentResolver,
                    inImage,
                    imageName,
                    null
                )
            )
        } catch (e: Exception) {
            Log.e("path", e.localizedMessage)
            println("message" + e.message)
        }
        return null
    }
    val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
                openCamera()
            } else {
                showToastLong(context,"Permission is required")
                // Explain to the user that the feature is unavailable because the
                // features requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
            }
        }

    fun permission() {

        context?.let {

            if(  ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED){
                openCamera()

            }else{
                requestPermissionLauncher.launch(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
          /*  when {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    // You can use the API that requires the permission.
                    openCamera()
                }
               *//* shouldShowRequestPermissionRationale(...) -> {
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected. In this UI,
                // include a "cancel" or "no thanks" button that allows the user to
                // continue using your app without granting the permission.
                showInContextUI(...)
            }*//*
                else -> {
                    // You can directly ask for the permission.
                    // The registered ActivityResultCallback gets the result of this request.
                    requestPermissionLauncher.launch(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            }*/
        }




      //OLD CODE for permission
      /*  context?.let {
            if (ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                != PackageManager.PERMISSION_GRANTED
            ) {

                // Permission is not granted
                // Should we show an explanation?
                activity?.let {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            it,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        )
                    ) {
                        // Show an explanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.
                    } else ActivityCompat.requestPermissions(
                        it,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        1001
                    )
                    // No explanation needed; request the permission
                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }

            } else {
                openCamera()
                //    showToastLong(it,"hello")

                // Permission has already been granted
            }
        }*/

    }


}





