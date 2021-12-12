package org.jfrog.build.extractor.builder;

import org.apache.commons.lang3.StringUtils;

import org.jfrog.build.api.release.PromotionStatus;
import org.jfrog.build.extractor.ci.Agent;
import org.jfrog.build.extractor.ci.BuildAgent;
import org.jfrog.build.extractor.ci.BuildInfo;
import org.jfrog.build.extractor.ci.BuildRetention;
import org.jfrog.build.extractor.ci.Issues;
import org.jfrog.build.extractor.ci.MatrixParameter;
import org.jfrog.build.extractor.ci.Module;
import org.jfrog.build.extractor.ci.Vcs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A builder for the build class
 */
public class BuildInfoBuilder {
    protected String version;
    protected String name;
    protected String started;
    protected long startedMillis;
    protected String number;
    protected String project;
    protected String artifactoryPluginVersion;
    protected Agent agent;
    protected BuildAgent buildAgent;
    protected long durationMillis;
    protected String principal;
    protected String artifactoryPrincipal;
    protected String url;
    protected String parentName;
    protected String parentNumber;
    protected List<Vcs> vcs = new ArrayList<Vcs>();
    protected String vcsRevision;
    protected String vcsUrl;
    protected List<MatrixParameter> runParameters;
    protected ConcurrentHashMap<String, Module> modules;
    protected List<PromotionStatus> statuses;
    protected Properties properties;
    protected BuildRetention buildRetention;
    protected Issues issues;

    public BuildInfoBuilder(String name) {
        this.name = name;
    }

    /**
     * Assembles the build class
     *
     * @return Assembled build
     */
    public BuildInfo build() {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Build must have a name");
        }
        if (StringUtils.isBlank(number)) {
            throw new IllegalArgumentException("Build number must be set");
        }
        if (StringUtils.isBlank(started)) {
            throw new IllegalArgumentException("Build start time must be set");
        }

        BuildInfo buildInfo = new BuildInfo();
        if (StringUtils.isNotBlank(version)) {
            buildInfo.setVersion(version);
        }
        buildInfo.setName(name);
        buildInfo.setNumber(number);
        buildInfo.setProject(project);
        buildInfo.setAgent(agent);
        buildInfo.setBuildAgent(buildAgent);
        buildInfo.setStarted(started);
        buildInfo.setStartedMillis(startedMillis);
        buildInfo.setDurationMillis(durationMillis);
        buildInfo.setPrincipal(principal);
        buildInfo.setArtifactoryPrincipal(artifactoryPrincipal);
        buildInfo.setArtifactoryPluginVersion(artifactoryPluginVersion);
        buildInfo.setUrl(url);
        buildInfo.setParentName(parentName);
        buildInfo.setParentNumber(parentNumber);
        buildInfo.setRunParameters(runParameters);
        buildInfo.setModules(modules != null ? new ArrayList<Module>(modules.values()) : null);
        buildInfo.setStatuses(statuses);
        buildInfo.setProperties(properties);
        buildInfo.setVcs(vcs);
        buildInfo.setBuildRetention(buildRetention);
        buildInfo.setIssues(issues);
        return buildInfo;
    }

    /**
     * Sets the build-info schema version
     *
     * @param version BuildInfo version
     * @return Builder instance
     */
    public BuildInfoBuilder version(String version) {
        this.version = version;
        return this;
    }

    /**
     * Sets the name of the build
     *
     * @param name Build name
     * @return Builder instance
     */
    public BuildInfoBuilder name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the number of the build
     *
     * @param number Build number
     * @return Builder instance
     */
    public BuildInfoBuilder number(String number) {
        this.number = number;
        return this;
    }

    /**
     * Sets the agent of the build
     *
     * @param agent Build agent
     * @return Builder instance
     */
    public BuildInfoBuilder agent(Agent agent) {
        this.agent = agent;
        return this;
    }

    /**
     * Sets the build agent of the build
     *
     * @param buildAgent The build agent
     * @return Builder instance
     */
    public BuildInfoBuilder buildAgent(BuildAgent buildAgent) {
        this.buildAgent = buildAgent;
        return this;
    }

    /**
     * Sets the start time of the build
     *
     * @param started Build started time
     * @return Builder instance
     */
    public BuildInfoBuilder started(String started) {
        this.started = started;
        return this;
    }

    /**
     * Sets the start time in millis of the build
     *
     * @return Builder instance
     */
    public BuildInfoBuilder startedMillis(long startedMillis) {
        this.startedMillis = startedMillis;
        return this;
    }

    /**
     * Sets the start time of the build
     *
     * @param startedDate Build started date
     * @return Builder instance
     */
    public BuildInfoBuilder startedDate(Date startedDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(BuildInfo.STARTED_FORMAT);
        this.started = simpleDateFormat.format(startedDate);
        this.startedMillis = startedDate.getTime();
        return this;
    }

    /**
     * Sets the duration milliseconds of the build
     *
     * @param durationMillis Build duration milliseconds
     * @return Builder instance
     */
    public BuildInfoBuilder durationMillis(long durationMillis) {
        this.durationMillis = durationMillis;
        return this;
    }

    /**
     * Sets the principal of the build
     *
     * @param principal Build principal
     * @return Builder instance
     */
    public BuildInfoBuilder principal(String principal) {
        this.principal = principal;
        return this;
    }

    /**
     * Sets the Artifactory principal of the build
     *
     * @param artifactoryPrincipal Build Artifactory principal
     * @return Builder instance
     */
    public BuildInfoBuilder artifactoryPrincipal(String artifactoryPrincipal) {
        this.artifactoryPrincipal = artifactoryPrincipal;
        return this;
    }

    public BuildInfoBuilder artifactoryPluginVersion(String artifactoryPluginVersion) {
        this.artifactoryPluginVersion = artifactoryPluginVersion;
        return this;
    }

    /**
     * Sets the URL of the build
     *
     * @param url Build URL
     * @return Builder instance
     */
    public BuildInfoBuilder url(String url) {
        this.url = url;
        return this;
    }

    /**
     * Sets the parent build name of the build
     *
     * @param parentName Build parent build name
     * @return Builder instance
     */
    public BuildInfoBuilder parentName(String parentName) {
        this.parentName = parentName;
        return this;
    }

    /**
     * Sets the parent build number of the build
     *
     * @param parentNumber Build parent build number
     * @return Builder instance
     */
    public BuildInfoBuilder parentNumber(String parentNumber) {
        this.parentNumber = parentNumber;
        return this;
    }

    /**
     * Sets the build vcs
     *
     * @param vcs The vcs data
     * @return Builder instance
     */
    public BuildInfoBuilder vcs(List<Vcs> vcs) {
        this.vcs = vcs;
        return this;
    }

    /**
     * Sets the vcs revision (format is vcs specific)
     *
     * @param vcsRevision The vcs revision
     * @return Builder instance
     * @deprecated {@link BuildInfoBuilder#vcs(List)} instead.
     */
    public BuildInfoBuilder vcsRevision(String vcsRevision) {
        this.vcsRevision = vcsRevision;
        return this;
    }

    /**
     * Sets the vcs url
     *
     * @param vcsUrl The vcs url
     * @return Builder instance
     * @deprecated {@link BuildInfoBuilder#vcs(List)} instead.
     */
    public BuildInfoBuilder vcsUrl(String vcsUrl) {
        this.vcsUrl = vcsUrl;
        return this;
    }

    /**
     * Sets the modules of the build
     *
     * @param modules Build modules
     * @return Builder instance
     */
    public BuildInfoBuilder modules(ConcurrentHashMap<String, Module> modules) {
        this.modules = modules;
        return this;
    }

    /**
     * Sets the modules of the build
     *
     * @param modules Build modules
     * @return Builder instance
     */
    public BuildInfoBuilder modules(List<Module> modules) {
        ConcurrentHashMap<String, Module> modulesMap = new ConcurrentHashMap<String, Module>();
        for (Module module : modules) {
            modulesMap.put(module.getId(), module);
        }

        this.modules = modulesMap;
        return this;
    }

    public BuildInfoBuilder statuses(List<PromotionStatus> statuses) {
        this.statuses = statuses;
        return this;
    }

    public BuildInfoBuilder addStatus(PromotionStatus promotionStatus) {
        if (statuses == null) {
            statuses = new ArrayList<>();
        }
        statuses.add(promotionStatus);
        return this;
    }

    /**
     * Sets the post build retention period
     *
     * @param buildRetention Build violation  recipients.
     * @return Builder instance
     */
    public BuildInfoBuilder buildRetention(BuildRetention buildRetention) {
        this.buildRetention = buildRetention;
        return this;
    }

    /**
     * Sets the post build retention period
     *
     * @param runParameters matrix parameters.
     * @return Builder instance
     */
    public BuildInfoBuilder buildRunParameters(List<MatrixParameter> runParameters) {
        this.runParameters = runParameters;
        return this;
    }

    /**
     * Sets the post build retention period
     *
     * @param parameter MatrixParameter.
     * @return Builder instance
     */
    public BuildInfoBuilder addRunParameters(MatrixParameter parameter) {
        if (runParameters == null) {
            runParameters = new ArrayList<>();
        }
        runParameters.add(parameter);

        return this;
    }

    /**
     * Adds the given module to the modules list
     *
     * @param module Module to add
     * @return Builder instance
     */
    public BuildInfoBuilder addModule(Module module) {
        if (modules == null) {
            synchronized (this) {
                if (modules == null) {
                    modules = new ConcurrentHashMap<String, Module>();
                }
            }
        }
        modules.put(module.getId(), module);
        return this;
    }

    /**
     * Sets the properties of the build
     *
     * @param properties Build properties
     * @return Builder instance
     */
    public BuildInfoBuilder properties(Properties properties) {
        this.properties = properties;
        return this;
    }

    /**
     * Adds the given property to the properties object
     *
     * @param key   Key of property to add
     * @param value Value of property to add
     * @return Builder instance
     */
    public BuildInfoBuilder addProperty(Object key, Object value) {
        if (properties == null) {
            properties = new Properties();
        }
        properties.put(key, value);
        return this;
    }

    public BuildInfoBuilder issues(Issues issues) {
        this.issues = issues;
        return this;
    }

    public BuildInfoBuilder setProject(String project) {
        this.project = project;
        return this;

    }
}