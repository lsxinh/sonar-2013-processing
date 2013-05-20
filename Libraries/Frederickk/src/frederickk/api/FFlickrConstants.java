package frederickk.api;

/**
 *  Frederickk.Api 0.0.5
 *  FFlickrConstants.java
 *
 *  Ken Frederick
 *  ken.frederick@gmx.de
 *
 *  http://cargocollective.com/kenfrederick/
 *  http://kenfrederick.blogspot.com/
 *
 *  a library for easier use of the flickr api
 *  http://github.com/frederickk/frederickk
 *
 */


public interface FFlickrConstants {
	//-----------------------------------------------------------------------------
	// services
	//-----------------------------------------------------------------------------
	static final String SERVICE_URL_REST = "http://api.flickr.com/services/rest/";
	static final String SERVICE_URL_XMLPRC = "http://api.flickr.com/services/xmlrpc/";


	
	//-----------------------------------------------------------------------------
	// api methods most useful
	//-----------------------------------------------------------------------------
	/**
	 * Returns the list of interesting photos for the most recent day or a user-specified date
	 * http://www.flickr.com/services/api/flickr.interestingness.getList.html
	 */
	static final String INTERESTINGNESS = "flickr.interestingness.getList";

	/**
	 * Returns the available sizes for a photo
	 * http://www.flickr.com/services/api/flickr.photos.getSizes.html
	 */
	static final String PHOTOS_GET_SIZES = "flickr.photos.getSizes";

	/**
	 * Return a list of photos matching some criteria
	 * http://www.flickr.com/services/api/flickr.photos.search.html
	 */
	static final String PHOTOS_SEARCH = "flickr.photos.search";

	/**
	 * Get the tag list for a given photo
	 * http://www.flickr.com/services/api/flickr.tags.getListPhoto.html
	 */
	static final String TAGS_LIST_PHOTO = "flickr.tags.getListPhoto";

	
	
	//-----------------------------------------------------------------------------
	// api methods all
	//-----------------------------------------------------------------------------
	static final String[] ACTIVITY = {
		"flickr.activity.userComments",
		"flickr.activity.userPhotos"
	};

	static final String[] AUTH = {
		"flickr.auth.checkToken",
		"flickr.auth.getFrob",
		"flickr.auth.getFullToken",
		"flickr.auth.getToken"
	};
	
	static final String[] BLOGS = {
		"flickr.blogs.getList",
		"flickr.blogs.getServices",
		"flickr.blogs.postPhoto"
	};

	static final String[] COLLECTIONS = {
		"flickr.collections.getInfo",
		"flickr.collections.getTree"
	};
		
	static final String FINALCOMMONS = "flickr.commons.getInstitutions";

	static final String[] CONTACTS = {
		"flickr.contacts.getList",
		"flickr.contacts.getListRecentlyUploaded",
		"flickr.contacts.getPublicList"
	};
	
	static final String[] FAVORITES = {
		"flickr.favorites.add",
		"flickr.favorites.getList",
		"flickr.favorites.getPublicList",
		"flickr.favorites.remove"
	};

	static final String[] GALLERIES = {
		"flickr.galleries.addPhoto",
		"flickr.galleries.create",
		"flickr.galleries.editMeta",
		"flickr.galleries.editPhoto",
		"flickr.galleries.editPhotos",
		"flickr.galleries.getInfo",
		"flickr.galleries.getList",
		"flickr.galleries.getListForPhoto",
		"flickr.galleries.getPhotos"
	};

	static final String[] GROUPS = {
		"flickr.groups.browse",
		"flickr.groups.getInfo",
		"flickr.groups.search"
	};

	static final String[] GROUPS_MEMBERS = {
		"flickr.groups.members.getList"
	};

	static final String[] GROUPS_POOLS = {
		"flickr.groups.pools.add",
		"flickr.groups.pools.getContext",
		"flickr.groups.pools.getGroups",
		"flickr.groups.pools.getPhotos",
		"flickr.groups.pools.remove"
	};
	

	static final String[] MACHINETAGS = {
		"flickr.machinetags.getNamespaces",
		"flickr.machinetags.getPairs",
		"flickr.machinetags.getPredicates",
		"flickr.machinetags.getRecentValues",
		"flickr.machinetags.getValues"
	};

	static final String[] PANDA = {
		"flickr.panda.getList",
		"flickr.panda.getPhotos"
	};

	static final String[] PEOPLE = {
		"flickr.people.findByEmail",
		"flickr.people.findByUsername",
		"flickr.people.getInfo",
		"flickr.people.getPhotos",
		"flickr.people.getPhotosOf",
		"flickr.people.getPublicGroups",
		"flickr.people.getPublicPhotos",
		"flickr.people.getUploadStatus"
	};

	static final String[] PHOTOS = {
		"flickr.photos.addTags",	//0
		"flickr.photos.delete",
		"flickr.photos.getAllContexts",
		"flickr.photos.getContactsPhotos",
		"flickr.photos.getContactsPublicPhotos",
		"flickr.photos.getContext",	//5
		"flickr.photos.getCounts",
		"flickr.photos.getExif",
		"flickr.photos.getFavorites",
		"flickr.photos.getInfo",
		"flickr.photos.getNotInSet",	//10
		"flickr.photos.getPerms",
		"flickr.photos.getRecent",
		"flickr.photos.getSizes",
		"flickr.photos.getUntagged",
		"flickr.photos.getWithGeoData",	//15
		"flickr.photos.getWithoutGeoData",
		"flickr.photos.recentlyUpdated",
		"flickr.photos.removeTag",
		"flickr.photos.search",
		"flickr.photos.setContentType",	//20
		"flickr.photos.setDates",
		"flickr.photos.setMeta",
		"flickr.photos.setPerms",
		"flickr.photos.setSafetyLevel",
		"flickr.photos.setTags"	//25
	};


	
	static final String[] PHOTOS_COMMENTS = {
		"flickr.photos.comments.addComment",
		"flickr.photos.comments.deleteComment",
		"flickr.photos.comments.editComment",
		"flickr.photos.comments.getList",
		"flickr.photos.comments.getRecentForContacts"
	};
	
	static final String[] PHOTOS_GEO = {
		"flickr.photos.geo.batchCorrectLocation",
		"flickr.photos.geo.correctLocation",
		"flickr.photos.geo.getLocation",
		"flickr.photos.geo.getPerms",
		"flickr.photos.geo.photosForLocation",
		"flickr.photos.geo.removeLocation",
		"flickr.photos.geo.setContext",
		"flickr.photos.geo.setLocation",
		"flickr.photos.geo.setPerms"
	};

	static final String[] PHOTOS_LICENSES = {
		"flickr.photos.licenses.getInfo",
		"flickr.photos.licenses.setLicense"
	};

	static final String[] PHOTOS_NOTES = {
		"flickr.photos.notes.add",
		"flickr.photos.notes.delete",
		"flickr.photos.notes.edit"
	};

	static final String[] PHOTOS_PEOPLE = {
		"flickr.photos.people.add",
		"flickr.photos.people.delete",
		"flickr.photos.people.deleteCoords",
		"flickr.photos.people.editCoords",
		"flickr.photos.people.getList"
	};

	static final String[] PHOTOS_TRANSFORM = {
		"flickr.photos.transform.rotate"
	};

	static final String[] PHOTOS_UPLOAD = {
		"flickr.photos.upload.checkTickets"
	};

	static final String[] PHOTOSETS = {
		"flickr.photosets.addPhoto",
		"flickr.photosets.create",
		"flickr.photosets.delete",
		"flickr.photosets.editMeta",
		"flickr.photosets.editPhotos",
		"flickr.photosets.getContext",
		"flickr.photosets.getInfo",
		"flickr.photosets.getList",
		"flickr.photosets.getPhotos",
		"flickr.photosets.orderSets",
		"flickr.photosets.removePhoto",
		"flickr.photosets.removePhotos",
		"flickr.photosets.reorderPhotos",
		"flickr.photosets.setPrimaryPhoto"
	};

	static final String[] PHOTOSETS_COMMENTS = {
		"flickr.photosets.comments.addComment",
		"flickr.photosets.comments.deleteComment",
		"flickr.photosets.comments.editComment",
		"flickr.photosets.comments.getList"
	};

	static final String[] PLACES = {
		"flickr.places.find",
		"flickr.places.findByLatLon",
		"flickr.places.getChildrenWithPhotosPublic",
		"flickr.places.getInfo",
		"flickr.places.getInfoByUrl",
		"flickr.places.getPlaceTypes",
		"flickr.places.getShapeHistory",
		"flickr.places.getTopPlacesList",
		"flickr.places.placesForBoundingBox",
		"flickr.places.placesForContacts",
		"flickr.places.placesForTags",
		"flickr.places.placesForUser",
		"flickr.places.resolvePlaceId",
		"flickr.places.resolvePlaceURL",
		"flickr.places.tagsForPlace"
	};
	
	static final String[] PREFS = {
		"flickr.prefs.getContentType",
		"flickr.prefs.getGeoPerms",
		"flickr.prefs.getHidden",
		"flickr.prefs.getPrivacy",
		"flickr.prefs.getSafetyLevel"
	};
	
	static final String[] REFLECTION = {
		"flickr.reflection.getMethodInfo",
		"flickr.reflection.getMethods"
	};

	static final String[] STATS = {
		"flickr.stats.getCollectionDomains",
		"flickr.stats.getCollectionReferrers",
		"flickr.stats.getCollectionStats",
		"flickr.stats.getCSVFiles",
		"flickr.stats.getPhotoDomains",
		"flickr.stats.getPhotoReferrers",
		"flickr.stats.getPhotosetDomains",
		"flickr.stats.getPhotosetReferrers",
		"flickr.stats.getPhotosetStats",
		"flickr.stats.getPhotoStats",
		"flickr.stats.getPhotostreamDomains",
		"flickr.stats.getPhotostreamReferrers",
		"flickr.stats.getPhotostreamStats",
		"flickr.stats.getPopularPhotos",
		"flickr.stats.getTotalViews"
	};
		
	static final String[] TAGS = {
		"flickr.tags.getClusterPhotos",
		"flickr.tags.getClusters",
		"flickr.tags.getHotList",
		"flickr.tags.getListPhoto",
		"flickr.tags.getListUser",
		"flickr.tags.getListUserPopular",
		"flickr.tags.getListUserRaw",
		"flickr.tags.getRelated"
	};
	
	static final String[] TEST = {
		"flickr.test.echo",
		"flickr.test.login",
		"flickr.test.null"
	};

	static final String[] URLS = {
		"flickr.urls.getGroup",
		"flickr.urls.getUserPhotos",
		"flickr.urls.getUserProfile",
		"flickr.urls.lookupGallery",
		"flickr.urls.lookupGroup",
		"flickr.urls.lookupUser"
	};
	
}