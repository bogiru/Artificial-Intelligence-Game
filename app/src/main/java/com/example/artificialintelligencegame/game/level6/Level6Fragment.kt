package com.example.artificialintelligencegame.game.level6

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.*
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.artificialintelligencegame.R
import com.example.artificialintelligencegame.databinding.Level6FragmentBinding
import com.example.artificialintelligencegame.util.REQUEST_CODE_PERMISSIONS
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.ObjectDetector
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions
import com.google.mlkit.vision.objects.defaults.PredefinedCategory
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import java.util.concurrent.Executors
import org.koin.android.viewmodel.ext.android.viewModel


private const val REQUEST_CODE_PERMISSIONS = 10

private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)

class Level4Fragment : Fragment() {

    private val viewModel: Level6ViewModel by viewModel()

    private lateinit var binding: Level6FragmentBinding

    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private lateinit var previewView: PreviewView
    private lateinit var imagePreview: Preview

    private lateinit var imageAnalysis: ImageAnalysis
    private val executor = Executors.newSingleThreadExecutor()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        configureBinding(inflater, container)
        configureCameraX()
        setupObserverViewModel()

        return binding.root
    }

    private fun configureBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.level6_fragment, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setupObserverViewModel() {
        viewModel.showNewRobotText.observe(viewLifecycleOwner, Observer {
            showNewText(it)
        })
    }

    private fun showNewText(index: Int) {
        val animAppearance = AnimationUtils.loadAnimation(this.context, R.anim.appearance)
        val animDisappearance = AnimationUtils.loadAnimation(this.context, R.anim.disappearance)

        binding.robotText.startAnimation(animDisappearance)

        when (index) {
            2 -> {
                binding.robotTextLevel6.text = activity!!.application.getString(R.string.text_robot_two_level6)
                binding.actionTextViewLevel6.text = activity!!.application.getString(R.string.answer_two_level6)
            }
            3 -> {
                binding.robotTextLevel6.text = activity!!.application.getString(R.string.text_robot_three_level6)
                binding.actionTextViewLevel6.text = activity!!.application.getString(R.string.answer_three_level6)
            }

            4 -> {
                if (viewModel.winner.value == "robot") {
                    binding.robotTextLevel6.text = activity!!.application.getString(R.string.text_robot_four_user_winner_level6)
                } else {
                    binding.robotTextLevel6.text = activity!!.application.getString(R.string.text_robot_four_user_winner_level6)
                }

                binding.actionTextViewLevel6.text = activity!!.application.getString(R.string.answer_four_level6)
                viewModel.hideRobotLayout()
            }

            5 -> {
                binding.robotTextLevel6.text = activity!!.application.getString(R.string.text_robot_five_level6)
                binding.actionTextViewLevel6.text = activity!!.application.getString(R.string.answer_five_level6)

            }

            6 -> findNavController().navigate(R.id.mapFragment2)
        }

        binding.robotText.startAnimation(animAppearance)
    }

    private fun configureCameraX() {
        previewView = binding.viewFinder

        cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        if (allPermissionsGranted()) {
            previewView.post { startCamera() }
        } else {
            requestPermissions(
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    private fun startCamera() {
        imagePreview = Preview.Builder().apply {
            setTargetAspectRatio(AspectRatio.RATIO_16_9)
            setTargetRotation(previewView.display.rotation)
        }.build()

        imageAnalysis = ImageAnalysis.Builder().apply {
            setImageQueueDepth(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
        }.build()
        imageAnalysis.setAnalyzer(executor, LuminosityAnalyzer(viewModel))

        val cameraSelector = CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()
        cameraProviderFuture.addListener(Runnable {
            val cameraProvider = cameraProviderFuture.get()
            val camera = cameraProvider.bindToLifecycle(this, cameraSelector, imagePreview, imageAnalysis)

            previewView.preferredImplementationMode = PreviewView.ImplementationMode.TEXTURE_VIEW
            imagePreview.setSurfaceProvider(previewView.createSurfaceProvider())
        }, ContextCompat.getMainExecutor(context))
    }

    private class LuminosityAnalyzer(private val viewModel: Level6ViewModel) : ImageAnalysis.Analyzer {

        init {
            configureML()
        }

        private lateinit var optionsML: ObjectDetectorOptions
        private lateinit var objectDetector: ObjectDetector


        private fun configureML() {
            optionsML = ObjectDetectorOptions.Builder()
                .setDetectorMode(ObjectDetectorOptions.STREAM_MODE)
                .enableClassification()  // Optional
                .build()

            objectDetector = ObjectDetection.getClient(optionsML)
        }

        @SuppressLint("UnsafeExperimentalUsageError")
        override fun analyze(imageProxy: ImageProxy) {

            val bitmapImage = imageProxy.image?.let { toBitmap(it) }
            val inputImage = InputImage.fromBitmap(bitmapImage!!, 0)


            objectDetector.process(inputImage)
                .addOnSuccessListener { detectedObjects ->
                    for (detectedObject in detectedObjects) {
                        for (label in detectedObject.labels) {
                            val text = label.text
                            viewModel.objectIsEdible.value = (PredefinedCategory.FOOD == text)
                        }
                    }
                }
            imageProxy.close()
        }

        private fun toBitmap(image: Image): Bitmap? {
            val planes: Array<Image.Plane> = image.planes
            val yBuffer: ByteBuffer = planes[0].buffer
            val uBuffer: ByteBuffer = planes[1].buffer
            val vBuffer: ByteBuffer = planes[2].buffer
            val ySize = yBuffer.remaining()
            val uSize = uBuffer.remaining()
            val vSize = vBuffer.remaining()
            val nv21 = ByteArray(ySize + uSize + vSize)
            //U and V are swapped
            yBuffer[nv21, 0, ySize]
            vBuffer[nv21, ySize, vSize]
            uBuffer[nv21, ySize + vSize, uSize]
            val yuvImage =
                YuvImage(nv21, ImageFormat.NV21, image.width, image.height, null)
            val out = ByteArrayOutputStream()
            yuvImage.compressToJpeg(Rect(0, 0, yuvImage.width, yuvImage.height), 75, out)
            val imageBytes: ByteArray = out.toByteArray()
            return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        }

    }
}