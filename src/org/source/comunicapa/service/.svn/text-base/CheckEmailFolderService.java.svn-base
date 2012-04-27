package org.source.comunicapa.service;

import java.io.File;

import org.source.comunicapa.R;
import org.source.comunicapa.utils.Constants;
import org.source.comunicapa.utils.Utils;

import android.app.IntentService;
import android.content.Intent;

public class CheckEmailFolderService extends IntentService {

	public CheckEmailFolderService() {
		super("CheckEmailFolderService");

	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		boolean b;

		if (Utils.isExternalStorageAvailable()) {

			String root = Utils.getExternalStorageFolder();

			Utils.log(root + File.pathSeparator + getString(R.string.app_name));

			File folderFile = new File(root + File.separator + getString(R.string.app_name));

			if (!folderFile.exists()) {

				b = folderFile.mkdirs();
				Utils.log("CominicaPA root folder added " + b);
			}

			File inboxFolderFile = new File(folderFile + File.separator + Constants.INBOX_FOLDER_NAME);
			if (!inboxFolderFile.exists()) {
				b = inboxFolderFile.mkdir();
				Utils.log("CominicaPA inbox folder added " + b);
			}

			File draftFolderFile = new File(folderFile + File.separator + Constants.DRAFT_FOLDER_NAME);
			if (!draftFolderFile.exists()) {
				b = draftFolderFile.mkdir();
				Utils.log("CominicaPA draft folder added " + b);
			}

			File sentFolderFile = new File(folderFile + File.pathSeparator + Constants.SENT_FOLDER_NAME);
			if (!sentFolderFile.exists()) {
				b = sentFolderFile.mkdir();
				Utils.log("CominicaPA sent folder added " + b);
			}

		}

	}

}
