package com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data.input.Provider;

import com.inrix.analytics.logging.LoggerFactory;
import com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data.input.Provider.enums.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by ravi on 1/23/2015.
 */
public class ProviderDetail {

    private static final String DEFAULT_VALUE = "Unknown";
    private int _id;
    private String _name;
    private String _providerTypeDesc;
    private String _drivingProfileDesc;
    private String _vehicleWeightClassDesc;
    private String _probeSourceTypeDesc;
    private String _probeIdRotationScheduleDesc;

    private ProviderType _providerType;
    private VehicleWeightClassType _vehicleWeightClass;
    private ProviderDrivingProfileType _providerDrivingProfile;
    private ProbeSourceType _probeSourceType;
    private ProbeIdRotationSchedule _probeIdRotationSchedule;

    public int getId() { return _id;}
    public String getIdHash() { return getProviderHash(new Integer(_id));}
    public String getName() { return _name;}
    public ProviderType getProviderType() { return _providerType;}
    public ProviderDrivingProfileType getDrivingProfile() { return _providerDrivingProfile;}
    public VehicleWeightClassType getVehicleWeightClass() { return _vehicleWeightClass;}
    public ProbeSourceType getProbeSourceType() { return _probeSourceType;}
    public ProbeIdRotationSchedule getIdRotationSchedule() {return _probeIdRotationSchedule;}

    public void setId(int value) {_id = value;}
    public void setName(String value) {_name = value;}
    public void setProviderType(ProviderType value) {_providerType = value;}
    public void setProviderDrivingProfile(ProviderDrivingProfileType value) {_providerDrivingProfile = value;}
    public void setVehicleWeightClass(VehicleWeightClassType value) {_vehicleWeightClass = value;}
    public void setProbeSourceType(ProbeSourceType value) {_probeSourceType = value;}
    public void setProbeIdRotationSchedule(ProbeIdRotationSchedule value) {_probeIdRotationSchedule = value;}

    public static String getProviderHash( Integer providerId) {
        return DigestUtils.md5Hex(providerId.toString().getBytes());
    }

    public void parseDetail( String line) throws Exception{
        String[] parts = line.split("\t");
        if (parts.length < 3)
            throw new IllegalArgumentException(String.format("providertype line item is not of expected format"));

        _name = parts[0].trim();
        _id = Integer.parseInt(parts[1].trim());
        _providerTypeDesc = parts[2].trim();

        if (parts.length > 6 ) {
            _drivingProfileDesc = parts[3].trim();
            _vehicleWeightClassDesc = parts[4].trim();
            _probeSourceTypeDesc = parts[5].trim();
            _probeIdRotationScheduleDesc = parts[6].trim();
        }

        // assign default value if null
        if (StringUtils.isBlank(_providerTypeDesc)) {
            _providerTypeDesc = DEFAULT_VALUE;
        }
        if (StringUtils.isBlank(_drivingProfileDesc)) {
            _drivingProfileDesc = DEFAULT_VALUE;
        }
        if (StringUtils.isBlank(_vehicleWeightClassDesc)) {
            _vehicleWeightClassDesc = DEFAULT_VALUE;
        }
        if (StringUtils.isBlank(_probeSourceTypeDesc)) {
            _probeSourceTypeDesc = DEFAULT_VALUE;
        }
        if (StringUtils.isBlank(_probeIdRotationScheduleDesc)) {
            _probeIdRotationScheduleDesc = DEFAULT_VALUE;
        }
        // adapt to enums
        _providerType = ProviderType.getTypeFromDescription(_providerTypeDesc);
        _providerDrivingProfile = ProviderDrivingProfileType.getTypeFromDescription(_drivingProfileDesc);
        _vehicleWeightClass = VehicleWeightClassType.getTypeFromDescription(_vehicleWeightClassDesc);
        _probeSourceType = ProbeSourceType.getTypeFromDescription(_probeSourceTypeDesc);
        _probeIdRotationSchedule = ProbeIdRotationSchedule.getTypeFromDescription(_probeIdRotationScheduleDesc);

        if (_providerType == null) {
            String error = String.format("providerType [%s] is not a recognized type", _providerTypeDesc);
            LoggerFactory.getLogger().error(null, error);
            throw new IllegalArgumentException(error);
        }
        if (_providerDrivingProfile == null) {
            String error = String.format("providerDrivingProfile [%s] is not a recognized type", _drivingProfileDesc);
            LoggerFactory.getLogger().error(null, error);
            throw new IllegalArgumentException(error);
        }
        if (_vehicleWeightClass == null) {
            String error = String.format("vehicleWeightClass [%s] is not a recognized type", _vehicleWeightClassDesc);
            LoggerFactory.getLogger().error(null, error);
            throw new IllegalArgumentException(error);
        }
        if (_probeSourceType == null) {
            String error = String.format("probeSourceType [%s] is not a recognized type", _probeSourceTypeDesc);
            LoggerFactory.getLogger().error(null, error);
            throw new IllegalArgumentException(error);
        }
        if (_probeIdRotationSchedule == null) {
            String error = String.format("_probeIdRotationSchedule [%s] is not a recognized type", _probeIdRotationScheduleDesc);
            LoggerFactory.getLogger().error(null, error);
            throw new IllegalArgumentException(error);
        }
    }
}

