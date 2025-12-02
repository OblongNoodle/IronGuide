package com.guidechecklist;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;
import java.awt.image.BufferedImage;
import com.google.gson.Gson;

@Slf4j
@PluginDescriptor(
		name = "B0aty HCIM Guide",
		description = "B0aty's HCIM Guide V3 checklist",
		tags = {"guide", "checklist", "ironman", "hcim", "b0aty"}
)
public class GuideChecklistPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ClientToolbar clientToolbar;

	private GuideChecklistPanel panel;
	private NavigationButton navButton;

	@Inject
	private Gson gson;

	@Override
	protected void startUp() throws Exception
	{
		log.info("B0aty HCIM Guide Checklist started!");

		panel = new GuideChecklistPanel(gson);

		final BufferedImage icon = ImageUtil.loadImageResource(getClass(), "/icon.png");

		navButton = NavigationButton.builder()
				.tooltip("B0aty HCIM Guide")
				.icon(icon)
				.priority(5)
				.panel(panel)
				.build();

		clientToolbar.addNavigation(navButton);
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("B0aty HCIM Guide Checklist stopped!");
		clientToolbar.removeNavigation(navButton);
	}

}