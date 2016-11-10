package com.inrix.analytics.trip.metrics.monitor.resources.v1.check.InputDataLoader.ProviderDetails;

import com.amazonaws.services.s3.AmazonS3Client;
import com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data.input.Provider.ProviderDetail;
import com.inrix.security.aws.S3ObjectStore;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by ravi on 1/23/2015.
 */
public class ProviderDetailsS3Source {

    S3ObjectStore store;

    //useful for mocking
    public ProviderDetailsS3Source(S3ObjectStore store) {
        store = store;
    }

    public ProviderDetailsS3Source() {
        store = new S3ObjectStore(new AmazonS3Client());
    }

    /*public DALResponse getProviderDetails( GetProviderDetailsRequest request) {
        final IRequestContext requestContext = request.getRequestContext();
        ILogContext logContext = LoggerFactory.getLogger().start(requestContext);

        final LinkedList<IDALResponseObject> dalResponseObjects = new LinkedList<>();

        try {
            Map<String, ProviderDetail> providerDetailsMap = loadProviderData(_store);
            if (providerDetailsMap != null && providerDetailsMap.size() > 0) {
                dalResponseObjects.add(new GetProviderDetailsResponseObject(providerDetailsMap));
            }
        }
        catch (Exception ex) {
            LoggerFactory.getLogger().error(requestContext, ex);

            IDALResponseObject dalResponseObject = new ProviderDetailsResponseException(ex.getMessage(), ex);
            dalResponseObjects.add(dalResponseObject);
        }

        return new DALResponse(dalResponseObjects);
    }*/

    //Loads a map of Provider names to Provider IDs and Provider types
    /*protected List<ProviderDetail> loadproviderData(IObjectStore store) throws Exception {

        List<ProviderDetail> retList = new ArrayList<ProviderDetail>();

        InputStream providersStream;
        providersStream = store.getContent(getProviderDetailsFileBucket(), getProviderDetailsFilePrefix());
        BufferedReader providerReader = new BufferedReader(new InputStreamReader(providersStream));
        String line;

        try {

            //expected line format: <providername>\t<providerid>\t<providertype>
            while ((line = providerReader.readLine()) != null) {
                ProviderDetail pd = new ProviderDetail();
                pd.parseDetail(line);
                retList.add(pd);
            }
        }
        finally {
            if (providerReader != null)
                providerReader.close();

            if (providersStream != null)
                providersStream.close();
        }

        return retList;
    }*/

    //Loads a map of provider id hash to provider types, driving profile and vehicle weight class
    public Map<Integer, ProviderDetail> loadProviderData() throws Exception {

        Map<Integer, ProviderDetail> providerDetailsMap = new LinkedHashMap<Integer, ProviderDetail>();

        InputStream providersStream;
        providersStream = store.getContent(getProviderDetailsFileBucket(), getProviderDetailsFilePrefix());
        BufferedReader providerReader = new BufferedReader(new InputStreamReader(providersStream));
        String line;

        try {

            //expected line format: <providername>\t<providerid>\t<providertype>
            while ((line = providerReader.readLine()) != null) {
                ProviderDetail pd = new ProviderDetail();
                pd.parseDetail(line);
                //String providerHash = ProviderDetail.getProviderHash(pd.getId());
                //providerDetailsMap.put(providerHash, pd);
                providerDetailsMap.put(pd.getId(), pd);
            }
        }
        finally {
            if (providerReader != null)
                providerReader.close();

            if (providersStream != null)
                providersStream.close();
        }

        return providerDetailsMap;
    }


    protected String getProviderDetailsFileBucket() {
        return "inrixprod-referencedata";
    }

    protected String getProviderDetailsFilePrefix() {
        return "data/dbname=rdd/tablename=providertype/data/rdd.providertype.txt";
    }

    public void close() {
        if (store != null)
            store = null;
    }
}
