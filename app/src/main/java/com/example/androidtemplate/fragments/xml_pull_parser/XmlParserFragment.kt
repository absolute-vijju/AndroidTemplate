package com.example.androidtemplate.fragments.xml_pull_parser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.androidtemplate.R
import com.example.androidtemplate.databinding.FragmentXmlParserBinding
import com.example.androidtemplate.util.BaseFragment
import com.example.androidtemplate.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.xml.sax.InputSource
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.net.HttpURLConnection
import javax.xml.parsers.DocumentBuilderFactory

class XmlParserFragment : BaseFragment() {

    private lateinit var mBinding: FragmentXmlParserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentXmlParserBinding.inflate(inflater)
        return mBinding.root
    }

    /**
     * Steps:
     *
     * 1. Get document builder
     * 2. Get document
     * 3. Normalize the xml structure
     * 4. Get all element by tag name
     */

    /**
     * Helper methods:
     *
     * getEventType(): This method is used to get the event type.
     * getAttributeValue(): This method is used to get the attribute value of a particular tag.
     * getAttributeCount(): It returns the total number of attributes of the current tag.
     * getAttributeName(int index): It returns the attribute name at a particular index.
     * getColumnNumber(): It returns the current column number using a 0 based indexing.
     * getName(): It returns the name of the tag.
     * getText(): It returns the text present in a particular element.
     */

    override fun init() {
//        type1()
        type2()
//        loadFromURL()
//        loadFromURL2()
    }

    private fun type2() {
        // Uncomment type2 in users.xml also
        mBinding.tvResult.text = getString(R.string.parsing_local_data).plus("\n\n")

        //        1. Get document builder
        val documentBuilderFactory = DocumentBuilderFactory.newInstance()
        val documentBuilder = documentBuilderFactory.newDocumentBuilder()

//        2. Get document
        val inputStream = requireContext().assets.open("users.xml")
        val document = documentBuilder.parse(inputStream)

//        3. Normalize the xml structure
        document.documentElement.normalize()

//        4. Get all element by tag name
        val usersNodeList = document.getElementsByTagName("user")

        for (userIndex in 0 until usersNodeList.length) {

            val userNode = usersNodeList.item(userIndex)

            if (userNode.nodeType == Node.ELEMENT_NODE) {

                val userElement = userNode as Element
                mBinding.tvResult.append("User: " + userElement.getAttribute("name") + "\n")
                val userDataList = userElement.childNodes

                for (userDataIndex in 0 until userDataList.length) {
                    val userData = userDataList.item(userDataIndex)
                    if (userData.nodeType == Node.ELEMENT_NODE) {
                        val userDataElement = userData as Element
                        mBinding.tvResult.append(
                            userDataElement.tagName + ": " + userDataElement.getAttribute(
                                "value"
                            ) + "\n"
                        )
                    }

                }

            }


        }

    }

    private fun type1() {
        // Uncomment type1 in users.xml also
        mBinding.tvResult.text = getString(R.string.parsing_local_data).plus("\n\n")

        //        1. Get document builder
        val documentBuilderFactory = DocumentBuilderFactory.newInstance()
        val documentBuilder = documentBuilderFactory.newDocumentBuilder()

//        2. Get document
        val inputStream = requireContext().assets.open("users.xml")
        val document = documentBuilder.parse(inputStream)

//        3. Normalize the xml structure
        document.documentElement.normalize()

//        4. Get all element by tag name
        val usersNodeList = document.getElementsByTagName("user")

        for (userIndex in 0 until usersNodeList.length) {

            val userNode = usersNodeList.item(userIndex)

            if (userNode.nodeType == Node.ELEMENT_NODE) {

                val userElement = userNode as Element

                val userDataList = userElement.childNodes

                for (userDataIndex in 0 until userDataList.length) {
                    val userData = userDataList.item(userDataIndex)
                    if (userData.nodeType == Node.ELEMENT_NODE) {
                        val userDataElement = userData as Element
                        mBinding.tvResult.append(
                            userDataElement.tagName + ": " + getValue(
                                userDataElement
                            ) + "\n"
                        )
                    }

                }

            }


        }

    }

    private fun getValue(element: Element): String {
        val nodeList = element.childNodes
        val node = nodeList.item(0)
        return node.nodeValue
    }

    override fun setListener() {

    }

    private fun loadFromURL() {
        mBinding.tvResult.text = getString(R.string.parsing_online_data).plus("\n\n")

        lifecycleScope.launch(Dispatchers.IO) {

            val url = java.net.URL(Constants.XML_PARSING_URL)
//            1. Get document builder
            val documentBuilderFactory = DocumentBuilderFactory.newInstance()
            val documentBuilder = documentBuilderFactory.newDocumentBuilder()

//            2.Get document
            val inputStream = InputSource(url.openStream())
            val document = documentBuilder.parse(inputStream)

//            3. Normalize the xml structure
            document.documentElement.normalize()

//            4.Get all element by tag name
            val entriesList = document.getElementsByTagName("entry")

            for (entryIndex in 0 until entriesList.length) {

                val entryNode = entriesList.item(entryIndex)
                if (entryNode.nodeType == Node.ELEMENT_NODE) {

                    val entryElement = entryNode as Element

                    val entryElementNode = entryElement.childNodes

                    for (entryElementNodeIndex in 0 until entryElementNode.length) {

                        val entryDataNode = entryElementNode.item(entryElementNodeIndex)
                        if (entryDataNode.nodeType == Node.ELEMENT_NODE) {

                            val entryDataElement = entryDataNode as Element

                            if (entryDataElement.hasChildNodes()) {
                                this.launch(Dispatchers.Main) {
                                    if (entryDataElement.tagName == "title" || entryDataElement.tagName == "author" || entryDataElement.tagName == "summary") {
                                        if (entryDataElement.tagName == "author") {
                                            for (i in 0 until entryDataElement.childNodes.length) {

                                                if (entryDataElement.childNodes.item(i).nodeType == Node.ELEMENT_NODE) {
                                                    val authorData =
                                                        entryDataElement.childNodes.item(i) as Element

                                                    if (authorData.tagName == "name")
                                                        mBinding.tvResult.append(
                                                            authorData.tagName + ": " + getValue(
                                                                authorData
                                                            ) + "\n"
                                                        )
//                                                    Timber.e("Document: " + authorData.tagName + ": " + getValue(authorData) + "\n")
                                                }
                                            }

                                        } else {
                                            mBinding.tvResult.append(
                                                entryDataElement.tagName + ": " + getValue(
                                                    entryDataElement
                                                ) + "\n"
                                            )
//                                        Timber.e("Document: " + entryDataElement.tagName + ": " + getValue(entryDataElement) + "\n")
                                        }
                                    }
                                }
                            }

                        }


                    }

                }

            }

        }
    }

    private fun loadFromURL2() {
        mBinding.tvResult.text = getString(R.string.parsing_online_data).plus("\n\n")

        lifecycleScope.launch(Dispatchers.IO) {

            val data = StringBuilder()

            val url = java.net.URL(Constants.XML_PARSING_URL)
            val httpUrlConnection = url.openConnection() as HttpURLConnection
            httpUrlConnection.readTimeout = 10000
            httpUrlConnection.connectTimeout = 15000
            httpUrlConnection.requestMethod = "GET"
            httpUrlConnection.doInput = true
            httpUrlConnection.connect()
            val inputStream = httpUrlConnection.inputStream

            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val parser = factory.newPullParser()

            parser.setInput(inputStream, null)

            var event = parser.eventType

            while (event != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name

                when (event) {
                    XmlPullParser.START_TAG -> {
                        if (tagName == "name") {
                            data.append("$tagName: ${parser.nextText()}\n")
                        }
                    }
                }
                event = parser.next()
            }
            this.launch(Dispatchers.Main) {
                mBinding.tvResult.append(data)
            }
        }
    }
}